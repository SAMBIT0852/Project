package com.cosmos.vcs.service;

import com.cosmos.vcs.model.FileChange;
import com.cosmos.vcs.util.HashUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FileTrackingService {
    
    private final RepositoryService repositoryService;
    
    public FileTrackingService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
    
    public List<FileChange> scanForChanges(String repositoryPath, Set<String> trackedFiles) throws IOException {
        List<FileChange> changes = new ArrayList<>();
        Set<String> ignorePatterns = repositoryService.loadIgnorePatterns(repositoryPath);
        
        Path repoPath = Paths.get(repositoryPath);
        Map<String, String> currentFileHashes = new HashMap<>();
        
        Files.walkFileTree(repoPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String relativePath = repoPath.relativize(file).toString().replace('\\', '/');
                
                if (shouldIgnoreFile(relativePath, ignorePatterns)) {
                    return FileVisitResult.CONTINUE;
                }
                
                String fileHash = HashUtil.calculateFileHash(file);
                currentFileHashes.put(relativePath, fileHash);
                
                return FileVisitResult.CONTINUE;
            }
            
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                String relativePath = repoPath.relativize(dir).toString().replace('\\', '/');
                
                if (shouldIgnoreFile(relativePath, ignorePatterns)) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                
                return FileVisitResult.CONTINUE;
            }
        });
        
        Map<String, String> trackedFileHashes = loadTrackedFileHashes(repositoryPath, trackedFiles);
        
        for (Map.Entry<String, String> entry : currentFileHashes.entrySet()) {
            String filePath = entry.getKey();
            String currentHash = entry.getValue();
            
            if (!trackedFiles.contains(filePath)) {
                changes.add(new FileChange(filePath, FileChange.ChangeType.ADDED, 
                    currentHash, Files.size(repoPath.resolve(filePath))));
            } else {
                String trackedHash = trackedFileHashes.get(filePath);
                if (!currentHash.equals(trackedHash)) {
                    changes.add(new FileChange(filePath, FileChange.ChangeType.MODIFIED, 
                        currentHash, Files.size(repoPath.resolve(filePath))));
                }
            }
        }
        
        for (String trackedFile : trackedFiles) {
            if (!currentFileHashes.containsKey(trackedFile)) {
                changes.add(new FileChange(trackedFile, FileChange.ChangeType.DELETED, 
                    null, 0));
            }
        }
        
        return changes;
    }
    
    public List<String> getUntrackedFiles(String repositoryPath, Set<String> trackedFiles) throws IOException {
        Set<String> ignorePatterns = repositoryService.loadIgnorePatterns(repositoryPath);
        Path repoPath = Paths.get(repositoryPath);
        List<String> untrackedFiles = new ArrayList<>();
        
        Files.walkFileTree(repoPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String relativePath = repoPath.relativize(file).toString().replace('\\', '/');
                
                if (shouldIgnoreFile(relativePath, ignorePatterns)) {
                    return FileVisitResult.CONTINUE;
                }
                
                if (!trackedFiles.contains(relativePath)) {
                    untrackedFiles.add(relativePath);
                }
                
                return FileVisitResult.CONTINUE;
            }
            
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                String relativePath = repoPath.relativize(dir).toString().replace('\\', '/');
                
                if (shouldIgnoreFile(relativePath, ignorePatterns)) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                
                return FileVisitResult.CONTINUE;
            }
        });
        
        return untrackedFiles;
    }
    
    public void addFilesToTracking(String repositoryPath, List<String> filePaths) throws IOException {
        var repository = repositoryService.loadRepository(repositoryPath);
        Set<String> trackedFiles = repository.getTrackedFiles();
        
        if (trackedFiles == null) {
            trackedFiles = new HashSet<>();
        }
        
        for (String filePath : filePaths) {
            Path fullPath = Paths.get(repositoryPath, filePath);
            if (Files.exists(fullPath) && Files.isRegularFile(fullPath)) {
                trackedFiles.add(filePath);
            }
        }
        
        repository.setTrackedFiles(trackedFiles);
        repositoryService.saveRepository(repository);
    }
    
    public void removeFilesFromTracking(String repositoryPath, List<String> filePaths) throws IOException {
        var repository = repositoryService.loadRepository(repositoryPath);
        Set<String> trackedFiles = repository.getTrackedFiles();
        
        if (trackedFiles != null) {
            trackedFiles.removeAll(filePaths);
            repository.setTrackedFiles(trackedFiles);
            repositoryService.saveRepository(repository);
        }
    }
    
    public boolean isBinaryFile(Path filePath) throws IOException {
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            return false;
        }
        
        long fileSize = Files.size(filePath);
        if (fileSize == 0) {
            return false;
        }
        
        int bytesToCheck = (int) Math.min(8192, fileSize);
        byte[] buffer = new byte[bytesToCheck];
        
        try (var inputStream = Files.newInputStream(filePath)) {
            int bytesRead = inputStream.read(buffer);
            
            for (int i = 0; i < bytesRead; i++) {
                if (buffer[i] == 0) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean shouldIgnoreFile(String filePath, Set<String> ignorePatterns) {
        for (String pattern : ignorePatterns) {
            if (matchesPattern(filePath, pattern)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean matchesPattern(String filePath, String pattern) {
        if (pattern.endsWith("/**")) {
            String dirPattern = pattern.substring(0, pattern.length() - 3);
            return filePath.startsWith(dirPattern + "/") || filePath.equals(dirPattern);
        }
        
        if (pattern.contains("*")) {
            String regexPattern = pattern
                .replace(".", "\\.")
                .replace("*", ".*")
                .replace("?", ".");
            return Pattern.matches(regexPattern, filePath);
        }
        
        return filePath.equals(pattern) || filePath.startsWith(pattern + "/");
    }
    
    private Map<String, String> loadTrackedFileHashes(String repositoryPath, Set<String> trackedFiles) throws IOException {
        Map<String, String> hashes = new HashMap<>();
        Path repoPath = Paths.get(repositoryPath);
        
        for (String trackedFile : trackedFiles) {
            Path filePath = repoPath.resolve(trackedFile);
            if (Files.exists(filePath)) {
                hashes.put(trackedFile, HashUtil.calculateFileHash(filePath));
            }
        }
        
        return hashes;
    }
}