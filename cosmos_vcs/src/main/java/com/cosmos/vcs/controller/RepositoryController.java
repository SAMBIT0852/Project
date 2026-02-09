package com.cosmos.vcs.controller;

import com.cosmos.vcs.model.Repository;
import com.cosmos.vcs.service.RepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/repository")
@CrossOrigin(origins = "*")
public class RepositoryController {
    
    private final RepositoryService repositoryService;
    
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
    
    @PostMapping("/init")
    public ResponseEntity<?> initializeRepository(@RequestParam String path) {
        try {
            Repository repository = repositoryService.initializeRepository(path);
            return ResponseEntity.ok(repository);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/load")
    public ResponseEntity<?> loadRepository(@RequestParam String path) {
        try {
            Repository repository = repositoryService.loadRepository(path);
            return ResponseEntity.ok(repository);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkRepository(@RequestParam String path) {
        boolean isRepository = repositoryService.isRepository(path);
        return ResponseEntity.ok(isRepository);
    }
    
    @GetMapping("/ignore-patterns")
    public ResponseEntity<?> getIgnorePatterns(@RequestParam String repositoryPath) {
        try {
            var patterns = repositoryService.loadIgnorePatterns(repositoryPath);
            return ResponseEntity.ok(patterns);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
}