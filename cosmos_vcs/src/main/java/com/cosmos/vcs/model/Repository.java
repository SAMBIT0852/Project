package com.cosmos.vcs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    private String path;
    private String currentBranch;
    private List<String> branches;
    private String headCommitId;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModified;
    
    private Set<String> trackedFiles;
    private Set<String> ignoredPatterns;

    public Repository() {}

    public Repository(String path, String currentBranch) {
        this.path = path;
        this.currentBranch = currentBranch;
        this.createdAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public String getCurrentBranch() { return currentBranch; }
    public void setCurrentBranch(String currentBranch) { this.currentBranch = currentBranch; }

    public List<String> getBranches() { return branches; }
    public void setBranches(List<String> branches) { this.branches = branches; }

    public String getHeadCommitId() { return headCommitId; }
    public void setHeadCommitId(String headCommitId) { this.headCommitId = headCommitId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getLastModified() { return lastModified; }
    public void setLastModified(LocalDateTime lastModified) { this.lastModified = lastModified; }

    public Set<String> getTrackedFiles() { return trackedFiles; }
    public void setTrackedFiles(Set<String> trackedFiles) { this.trackedFiles = trackedFiles; }

    public Set<String> getIgnoredPatterns() { return ignoredPatterns; }
    public void setIgnoredPatterns(Set<String> ignoredPatterns) { this.ignoredPatterns = ignoredPatterns; }

    @Override
    public String toString() {
        return "Repository{" +
                "path='" + path + '\'' +
                ", currentBranch='" + currentBranch + '\'' +
                ", headCommitId='" + headCommitId + '\'' +
                '}';
    }
}