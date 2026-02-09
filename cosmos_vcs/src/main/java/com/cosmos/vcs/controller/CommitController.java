package com.cosmos.vcs.controller;

import com.cosmos.vcs.model.Commit;
import com.cosmos.vcs.service.CommitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/commit")
@CrossOrigin(origins = "*")
public class CommitController {
    
    private final CommitService commitService;
    
    public CommitController(CommitService commitService) {
        this.commitService = commitService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> createCommit(@RequestParam String repositoryPath,
                                         @RequestParam String message,
                                         @RequestParam(defaultValue = "user") String author) {
        try {
            Commit commit = commitService.createCommit(repositoryPath, message, author);
            return ResponseEntity.ok(commit);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/history")
    public ResponseEntity<?> getCommitHistory(@RequestParam String repositoryPath,
                                             @RequestParam(defaultValue = "20") int limit) {
        try {
            List<Commit> history = commitService.getCommitHistory(repositoryPath, limit);
            return ResponseEntity.ok(history);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/{commitId}")
    public ResponseEntity<?> getCommit(@RequestParam String repositoryPath,
                                      @PathVariable String commitId) {
        try {
            Commit commit = commitService.loadCommit(repositoryPath, commitId);
            if (commit == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(commit);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutCommit(@RequestParam String repositoryPath,
                                           @RequestParam String commitId) {
        try {
            commitService.checkoutCommit(repositoryPath, commitId);
            return ResponseEntity.ok("Successfully checked out commit: " + commitId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/diff")
    public ResponseEntity<?> getDiff(@RequestParam String repositoryPath,
                                    @RequestParam String commitId1,
                                    @RequestParam String commitId2) {
        try {
            List<String> diff = commitService.getDiffBetweenCommits(repositoryPath, commitId1, commitId2);
            return ResponseEntity.ok(diff);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("IO Error: " + e.getMessage());
        }
    }
}