package com.cosmos.vcs.cli;

import com.cosmos.vcs.model.Commit;
import com.cosmos.vcs.model.FileChange;
import com.cosmos.vcs.model.Repository;
import com.cosmos.vcs.service.CommitService;
import com.cosmos.vcs.service.FileTrackingService;
import com.cosmos.vcs.service.RepositoryService;
import com.cosmos.vcs.util.HashUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class CosmosCliCommand implements CommandLineRunner {
    
    private final RepositoryService repositoryService;
    private final CommitService commitService;
    private final FileTrackingService fileTrackingService;
    
    public CosmosCliCommand(RepositoryService repositoryService,
                           CommitService commitService,
                           FileTrackingService fileTrackingService) {
        this.repositoryService = repositoryService;
        this.commitService = commitService;
        this.fileTrackingService = fileTrackingService;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            return; // Start web UI
        }
        
        String command = args[0];
        String currentDir = System.getProperty("user.dir");
        
        try {
            switch (command.toLowerCase()) {
                case "init":
                    handleInit(currentDir);
                    break;
                case "commit":
                    handleCommit(args, currentDir);
                    break;
                case "log":
                    handleLog(currentDir);
                    break;
                case "checkout":
                    handleCheckout(args, currentDir);
                    break;
                case "diff":
                    handleDiff(args, currentDir);
                    break;
                case "status":
                    handleStatus(currentDir);
                    break;
                case "add":
                    handleAdd(args, currentDir);
                    break;
                default:
                    printUsage();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
        
        System.exit(0);
    }
    
    private void handleInit(String path) throws IOException {
        Repository repository = repositoryService.initializeRepository(path);
        System.out.println("Initialized Cosmos repository in " + repository.getPath());
        System.out.println("Created branch: " + repository.getCurrentBranch());
    }
    
    private void handleCommit(String[] args, String repositoryPath) throws IOException {
        String message = null;
        String author = "user";
        
        for (int i = 1; i < args.length; i++) {
            if ("-m".equals(args[i]) && i + 1 < args.length) {
                message = args[i + 1];
                i++;
            } else if ("--author".equals(args[i]) && i + 1 < args.length) {
                author = args[i + 1];
                i++;
            }
        }
        
        if (message == null) {
            System.err.println("Error: Commit message is required. Use -m \"message\"");
            return;
        }
        
        Commit commit = commitService.createCommit(repositoryPath, message, author);
        System.out.println("Created commit: " + HashUtil.generateShortHash(commit.getId()));
        System.out.println("Message: " + commit.getMessage());
        System.out.println("Files changed: " + commit.getChanges().size());
    }
    
    private void handleLog(String repositoryPath) throws IOException {
        List<Commit> commits = commitService.getCommitHistory(repositoryPath, 10);
        
        if (commits.isEmpty()) {
            System.out.println("No commits found.");
            return;
        }
        
        for (Commit commit : commits) {
            System.out.println("Commit: " + HashUtil.generateShortHash(commit.getId()));
            System.out.println("Author: " + commit.getAuthor());
            System.out.println("Date: " + commit.getTimestamp());
            System.out.println("Message: " + commit.getMessage());
            System.out.println("Files: " + commit.getChanges().size() + " changed");
            System.out.println();
        }
    }
    
    private void handleCheckout(String[] args, String repositoryPath) throws IOException {
        if (args.length < 2) {
            System.err.println("Error: Commit ID is required");
            return;
        }
        
        String commitId = args[1];
        commitService.checkoutCommit(repositoryPath, commitId);
        System.out.println("Checked out commit: " + HashUtil.generateShortHash(commitId));
    }
    
    private void handleDiff(String[] args, String repositoryPath) throws IOException {
        if (args.length < 3) {
            System.err.println("Error: Two commit IDs are required");
            return;
        }
        
        String commitId1 = args[1];
        String commitId2 = args[2];
        
        List<String> diff = commitService.getDiffBetweenCommits(repositoryPath, commitId1, commitId2);
        
        for (String line : diff) {
            System.out.println(line);
        }
    }
    
    private void handleStatus(String repositoryPath) throws IOException {
        Repository repository = repositoryService.loadRepository(repositoryPath);
        List<FileChange> changes = fileTrackingService.scanForChanges(
            repositoryPath, repository.getTrackedFiles());
        List<String> untracked = fileTrackingService.getUntrackedFiles(
            repositoryPath, repository.getTrackedFiles());
        
        System.out.println("On branch: " + repository.getCurrentBranch());
        
        if (repository.getHeadCommitId() != null) {
            System.out.println("Last commit: " + HashUtil.generateShortHash(repository.getHeadCommitId()));
        }
        
        System.out.println();
        
        if (!changes.isEmpty()) {
            System.out.println("Changes to be committed:");
            for (FileChange change : changes) {
                System.out.println("  " + change.getChangeType().toString().toLowerCase() + ": " + change.getFilePath());
            }
            System.out.println();
        }
        
        if (!untracked.isEmpty()) {
            System.out.println("Untracked files:");
            for (String file : untracked) {
                System.out.println("  " + file);
            }
            System.out.println();
        }
        
        if (changes.isEmpty() && untracked.isEmpty()) {
            System.out.println("Working directory is clean.");
        }
    }
    
    private void handleAdd(String[] args, String repositoryPath) throws IOException {
        if (args.length < 2) {
            System.err.println("Error: File path is required");
            return;
        }
        
        List<String> filePaths = Arrays.asList(args).subList(1, args.length);
        fileTrackingService.addFilesToTracking(repositoryPath, filePaths);
        System.out.println("Added " + filePaths.size() + " file(s) to tracking");
    }
    
    private void printUsage() {
        System.out.println("Cosmos VCS - Local Version Control System");
        System.out.println();
        System.out.println("Usage: java -jar cosmos-vcs.jar [command] [options]");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  init                    Initialize a new repository");
        System.out.println("  commit -m \"message\"     Create a new commit");
        System.out.println("  log                     Show commit history");
        System.out.println("  checkout <commit-id>    Checkout a specific commit");
        System.out.println("  diff <id1> <id2>        Show differences between commits");
        System.out.println("  status                  Show working directory status");
        System.out.println("  add <file>...           Add files to tracking");
        System.out.println();
        System.out.println("If no command is provided, the web UI will start on http://localhost:8080");
    }
}