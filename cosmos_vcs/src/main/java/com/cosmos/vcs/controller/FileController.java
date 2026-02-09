package com.cosmos.vcs.controller;

import com.cosmos.vcs.model.FileChange;
import com.cosmos.vcs.service.FileTrackingService;
import com.cosmos.vcs.service.RepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileController {
    
    private final FileTrackingService fileTrackingService;
    private final RepositoryService repositoryService;
    
    public FileController(FileTrackingService fileTrackingService, 
                         RepositoryService repositoryService) {
        this.fileTrackingService = fileTrackingService;
        this.repositoryService = repositoryService;
    }
    
    @GetMapping("/changes")
    public ResponseEntity<?> getFileChanges(@RequestParam String repositoryPath) {
        try {
            var repository = repositoryService.loadRepository(repositoryPath);
            List<FileChange> changes = fileTrackingService.scanForChanges(
                repositoryPath, repository.getTrackedFiles());
            return ResponseEntity.ok(changes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/untracked")
    public ResponseEntity<?> getUntrackedFiles(@RequestParam String repositoryPath) {
        try {
            var repository = repositoryService.loadRepository(repositoryPath);
            List<String> untrackedFiles = fileTrackingService.getUntrackedFiles(
                repositoryPath, repository.getTrackedFiles());
            return ResponseEntity.ok(untrackedFiles);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addFilesToTracking(@RequestParam String repositoryPath,
                                               @RequestBody List<String> filePaths) {
        try {
            fileTrackingService.addFilesToTracking(repositoryPath, filePaths);
            return ResponseEntity.ok("Files added to tracking: " + filePaths.size());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/remove")
    public ResponseEntity<?> removeFilesFromTracking(@RequestParam String repositoryPath,
                                                    @RequestBody List<String> filePaths) {
        try {
            fileTrackingService.removeFilesFromTracking(repositoryPath, filePaths);
            return ResponseEntity.ok("Files removed from tracking: " + filePaths.size());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/tracked")
    public ResponseEntity<?> getTrackedFiles(@RequestParam String repositoryPath) {
        try {
            var repository = repositoryService.loadRepository(repositoryPath);
            return ResponseEntity.ok(repository.getTrackedFiles());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
}