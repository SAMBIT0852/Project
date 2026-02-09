package com.cosmos.vcs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileChange {
    public enum ChangeType {
        ADDED, MODIFIED, DELETED, RENAMED
    }

    private String filePath;
    private String oldPath;
    private ChangeType changeType;
    private String fileHash;
    private long fileSize;
    private boolean isBinary;

    public FileChange() {}

    public FileChange(String filePath, ChangeType changeType, String fileHash, long fileSize) {
        this.filePath = filePath;
        this.changeType = changeType;
        this.fileHash = fileHash;
        this.fileSize = fileSize;
        this.isBinary = false;
    }

    public FileChange(String filePath, String oldPath, ChangeType changeType, String fileHash, long fileSize) {
        this.filePath = filePath;
        this.oldPath = oldPath;
        this.changeType = changeType;
        this.fileHash = fileHash;
        this.fileSize = fileSize;
        this.isBinary = false;
    }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getOldPath() { return oldPath; }
    public void setOldPath(String oldPath) { this.oldPath = oldPath; }

    public ChangeType getChangeType() { return changeType; }
    public void setChangeType(ChangeType changeType) { this.changeType = changeType; }

    public String getFileHash() { return fileHash; }
    public void setFileHash(String fileHash) { this.fileHash = fileHash; }

    public long getFileSize() { return fileSize; }
    public void setFileSize(long fileSize) { this.fileSize = fileSize; }

    public boolean isBinary() { return isBinary; }
    public void setBinary(boolean binary) { isBinary = binary; }

    @Override
    public String toString() {
        return "FileChange{" +
                "filePath='" + filePath + '\'' +
                ", changeType=" + changeType +
                ", fileSize=" + fileSize +
                '}';
    }
}