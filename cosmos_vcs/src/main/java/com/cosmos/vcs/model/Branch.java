package com.cosmos.vcs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {
    private String name;
    private String headCommitId;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    
    private String parentBranch;

    public Branch() {}

    public Branch(String name, String headCommitId, String parentBranch) {
        this.name = name;
        this.headCommitId = headCommitId;
        this.parentBranch = parentBranch;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHeadCommitId() { return headCommitId; }
    public void setHeadCommitId(String headCommitId) { this.headCommitId = headCommitId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getParentBranch() { return parentBranch; }
    public void setParentBranch(String parentBranch) { this.parentBranch = parentBranch; }

    @Override
    public String toString() {
        return "Branch{" +
                "name='" + name + '\'' +
                ", headCommitId='" + headCommitId + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}