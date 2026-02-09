package com.cosmos.vcs.service;

import com.cosmos.vcs.model.Commit;
import com.cosmos.vcs.model.FileChange;
import com.cosmos.vcs.model.Repository;
import com.cosmos.vcs.util.HashUtil;
import com.cosmos.vcs.util.JsonUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitService {
    
    private final RepositoryService repositoryService;
    private final FileTrackingService fileTrackingService;
    private final CompressionService compressionService;
    
    public CommitService(RepositoryService repositoryService, 
                        FileTrackingService fileTrackingService,
                        CompressionService compressionService) {
        this.repositoryService = repositoryService;
        this.fileTrackingService = fileTrackingService;
        this.compressionService = compressionService;
    }
    
    public Commit createCommit(String repositoryPath, String message, String author) throws IOException {
        Repository repository = repositoryService.loadRepository(repositoryPath);
        
        List<FileChange> changes = fileTrackingService.scanForChanges(
            repositoryPath, repository.getTrackedFiles());
        
        if (changes.isEmpty()) {
            throw new IllegalStateException("No changes to commit");
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        String commitId = HashUtil.generateCommitId(message, timestamp, repository.getHeadCommitId());
        
        Commit commit = new Commit(commitId, message, timestamp, 
            repository.getHeadCommitId(), repository.getCurrentBranch(), changes, author);
        
        saveCommit(repositoryPath, commit);
        
        updateTrackedFiles(repository, changes);
        repository.setHeadCommitId(commitId);
        repositoryService.saveRepository(repository);
        
        return commit;
    }
    
    public List<Commit> getCommitHistory(String repositoryPath, int limit) throws IOException {
        Repository repository = repositoryService.loadRepository(repositoryPath);
        List<Commit> history = new ArrayList<>();
        
        String currentCommitId = repository.getHeadCommitId();
        int count = 0;
        
        while (currentCommitId != null && count < limit) {
            Commit commit = loadCommit(repositoryPath, currentCommitId);
            if (commit != null) {
                history.add(commit);
                currentCommitId = commit.getParentId();
                count++;
            } else {
                break;
            }
        }
        
        return history;
    }
    
    public Commit loadCommit(String repositoryPath, String commitId) throws IOException {
        Path commitPath = Paths.get(repositoryPath, ".cosmos", "commits", commitId, "commit.json");
        
        if (!Files.exists(commitPath)) {
            return null;
        }
        
        return JsonUtil.readFromFile(commitPath.toFile(), Commit.class);
    }
    
    public void checkoutCommit(String repositoryPath, String commitId) throws IOException {
        Repository repository = repositoryService.loadRepository(repositoryPath);
        Commit commit = loadCommit(repositoryPath, commitId);
        
        if (commit == null) {
            throw new IllegalArgumentException("Commit not found: " + commitId);
        }
        
        restoreFilesFromCommit(repositoryPath, commit);
        
        repository.setHeadCommitId(commitId);
        repositoryService.saveRepository(repository);
    }
    
    public List<String> getDiffBetweenCommits(String repositoryPath, String commitId1, String commitId2) throws IOException {
        Commit commit1 = loadCommit(repositoryPath, commitId1);
        Commit commit2 = loadCommit(repositoryPath, commitId2);
        
        if (commit1 == null || commit2 == null) {
            throw new IllegalArgumentException("One or both commits not found");
        }
        
        List<String> diff = new ArrayList<>();
        
        diff.add("Comparing commits:");
        diff.add("  " + commitId1 + " (" + commit1.getMessage() + ")");
        diff.add("  " + commitId2 + " (" + commit2.getMessage() + ")");
        diff.add("");
        
        List<FileChange> changes1 = commit1.getChanges();
        List<FileChange> changes2 = commit2.getChanges();
        
        for (FileChange change1 : changes1) {
            FileChange change2 = findFileChangeByPath(changes2, change1.getFilePath());
            
            if (change2 == null) {
                diff.add("- " + change1.getFilePath() + " (removed)");
            } else if (!change1.getFileHash().equals(change2.getFileHash())) {
                diff.add("M " + change1.getFilePath() + " (modified)");
            }
        }
        
        for (FileChange change2 : changes2) {
            FileChange change1 = findFileChangeByPath(changes1, change2.getFilePath());
            
            if (change1 == null) {
                diff.add("+ " + change2.getFilePath() + " (added)");
            }
        }
        
        return diff;
    }
    
    private void saveCommit(String repositoryPath, Commit commit) throws IOException {
        Path commitDir = Paths.get(repositoryPath, ".cosmos", "commits", commit.getId());
        Files.createDirectories(commitDir);
        
        JsonUtil.writeToFile(commit, commitDir.resolve("commit.json").toFile());
        
        for (FileChange change : commit.getChanges()) {
            if (change.getChangeType() != FileChange.ChangeType.DELETED) {
                saveFileSnapshot(repositoryPath, commitDir, change);
            }
        }
    }
    
    private void saveFileSnapshot(String repositoryPath, Path commitDir, FileChange change) throws IOException {
        Path sourceFile = Paths.get(repositoryPath, change.getFilePath());
        Path targetDir = commitDir.resolve("files").resolve(Paths.get(change.getFilePath()).getParent() != null ? 
            Paths.get(change.getFilePath()).getParent().toString() : "");
        
        Files.createDirectories(targetDir);
        
        Path targetFile = commitDir.resolve("files").resolve(change.getFilePath());
        
        if (Files.exists(sourceFile)) {
            if (compressionService.shouldCompress(sourceFile)) {
                compressionService.compressFile(sourceFile, targetFile.toString() + ".gz");
            } else {
                Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
    
    private void restoreFilesFromCommit(String repositoryPath, Commit commit) throws IOException {
        Path repoPath = Paths.get(repositoryPath);
        Path commitDir = Paths.get(repositoryPath, ".cosmos", "commits", commit.getId());
        
        for (FileChange change : commit.getChanges()) {
            Path targetFile = repoPath.resolve(change.getFilePath());
            
            if (change.getChangeType() == FileChange.ChangeType.DELETED) {
                if (Files.exists(targetFile)) {
                    Files.delete(targetFile);
                }
            } else {
                Path sourceFile = commitDir.resolve("files").resolve(change.getFilePath());
                Path compressedFile = Paths.get(sourceFile.toString() + ".gz");
                
                Files.createDirectories(targetFile.getParent());
                
                if (Files.exists(compressedFile)) {
                    compressionService.decompressFile(compressedFile.toString(), targetFile.toString());
                } else if (Files.exists(sourceFile)) {
                    Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
    
    private void updateTrackedFiles(Repository repository, List<FileChange> changes) {
        for (FileChange change : changes) {
            switch (change.getChangeType()) {
                case ADDED:
                case MODIFIED:
                    repository.getTrackedFiles().add(change.getFilePath());
                    break;
                case DELETED:
                    repository.getTrackedFiles().remove(change.getFilePath());
                    break;
                case RENAMED:
                    if (change.getOldPath() != null) {
                        repository.getTrackedFiles().remove(change.getOldPath());
                    }
                    repository.getTrackedFiles().add(change.getFilePath());
                    break;
            }
        }
    }
    
    private FileChange findFileChangeByPath(List<FileChange> changes, String filePath) {
        return changes.stream()
            .filter(change -> change.getFilePath().equals(filePath))
            .findFirst()
            .orElse(null);
    }
}