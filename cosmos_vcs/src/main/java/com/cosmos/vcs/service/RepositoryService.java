package com.cosmos.vcs.service;

import com.cosmos.vcs.model.Repository;
import com.cosmos.vcs.model.Branch;
import com.cosmos.vcs.util.JsonUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RepositoryService {
    
    private static final String COSMOS_DIR = ".cosmos";
    private static final String METADATA_FILE = "metadata.json";
    private static final String BRANCHES_FILE = "branches.json";
    private static final String INDEX_FILE = "index.json";
    
    public Repository initializeRepository(String repositoryPath) throws IOException {
        Path repoPath = Paths.get(repositoryPath);
        Path cosmosDir = repoPath.resolve(COSMOS_DIR);
        
        if (Files.exists(cosmosDir)) {
            throw new IllegalStateException("Repository already exists at: " + repositoryPath);
        }
        
        Files.createDirectories(cosmosDir);
        Files.createDirectories(cosmosDir.resolve("commits"));
        Files.createDirectories(cosmosDir.resolve("objects"));
        
        Repository repository = new Repository(repositoryPath, "main");
        repository.setBranches(Arrays.asList("main"));
        repository.setTrackedFiles(new HashSet<>());
        repository.setIgnoredPatterns(loadDefaultIgnorePatterns());
        
        saveRepository(repository);
        
        Branch mainBranch = new Branch("main", null, null);
        saveBranch(repositoryPath, mainBranch);
        
        createCosmosIgnoreFile(repoPath);
        
        return repository;
    }
    
    public Repository loadRepository(String repositoryPath) throws IOException {
        Path metadataPath = Paths.get(repositoryPath, COSMOS_DIR, METADATA_FILE);
        
        if (!Files.exists(metadataPath)) {
            throw new IllegalStateException("No repository found at: " + repositoryPath);
        }
        
        return JsonUtil.readFromFile(metadataPath.toFile(), Repository.class);
    }
    
    public void saveRepository(Repository repository) throws IOException {
        repository.setLastModified(LocalDateTime.now());
        Path metadataPath = Paths.get(repository.getPath(), COSMOS_DIR, METADATA_FILE);
        JsonUtil.writeToFile(repository, metadataPath.toFile());
    }
    
    public List<Branch> loadBranches(String repositoryPath) throws IOException {
        Path branchesPath = Paths.get(repositoryPath, COSMOS_DIR, BRANCHES_FILE);
        
        if (!Files.exists(branchesPath)) {
            return new ArrayList<>();
        }
        
        return JsonUtil.readListFromFile(branchesPath.toFile(), Branch.class);
    }
    
    public void saveBranch(String repositoryPath, Branch branch) throws IOException {
        List<Branch> branches = loadBranches(repositoryPath);
        
        branches.removeIf(b -> b.getName().equals(branch.getName()));
        branches.add(branch);
        
        Path branchesPath = Paths.get(repositoryPath, COSMOS_DIR, BRANCHES_FILE);
        JsonUtil.writeToFile(branches, branchesPath.toFile());
    }
    
    public boolean isRepository(String path) {
        return Files.exists(Paths.get(path, COSMOS_DIR, METADATA_FILE));
    }
    
    public Set<String> loadIgnorePatterns(String repositoryPath) throws IOException {
        Path ignoreFile = Paths.get(repositoryPath, ".cosmosignore");
        
        if (!Files.exists(ignoreFile)) {
            return loadDefaultIgnorePatterns();
        }
        
        Set<String> patterns = new HashSet<>();
        List<String> lines = Files.readAllLines(ignoreFile);
        
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty() && !line.startsWith("#")) {
                patterns.add(line);
            }
        }
        
        patterns.addAll(loadDefaultIgnorePatterns());
        return patterns;
    }
    
    private Set<String> loadDefaultIgnorePatterns() {
        Set<String> defaultPatterns = new HashSet<>();
        defaultPatterns.add(".cosmos/**");
        defaultPatterns.add("*.tmp");
        defaultPatterns.add("*.log");
        defaultPatterns.add(".DS_Store");
        defaultPatterns.add("Thumbs.db");
        defaultPatterns.add("node_modules/**");
        defaultPatterns.add("target/**");
        defaultPatterns.add("build/**");
        defaultPatterns.add("*.class");
        defaultPatterns.add("*.jar");
        return defaultPatterns;
    }
    
    private void createCosmosIgnoreFile(Path repoPath) throws IOException {
        Path ignoreFile = repoPath.resolve(".cosmosignore");
        
        if (!Files.exists(ignoreFile)) {
            List<String> defaultIgnoreContent = Arrays.asList(
                "# Cosmos VCS ignore patterns",
                "# Temporary files",
                "*.tmp",
                "*.bak",
                "*.swp",
                "",
                "# Logs",
                "*.log",
                "logs/",
                "",
                "# OS generated files",
                ".DS_Store",
                "Thumbs.db",
                "",
                "# IDE files",
                ".idea/",
                ".vscode/",
                "*.iml",
                "",
                "# Build outputs",
                "target/",
                "build/",
                "dist/",
                "node_modules/",
                "",
                "# Compiled files",
                "*.class",
                "*.o",
                "*.so",
                "*.dll"
            );
            
            Files.write(ignoreFile, defaultIgnoreContent);
        }
    }
}