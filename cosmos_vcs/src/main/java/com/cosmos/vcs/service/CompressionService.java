package com.cosmos.vcs.service;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CompressionService {
    
    private static final long COMPRESSION_THRESHOLD = 1024; // 1KB
    private static final int BUFFER_SIZE = 8192;
    
    public boolean shouldCompress(Path filePath) throws IOException {
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            return false;
        }
        
        long fileSize = Files.size(filePath);
        return fileSize > COMPRESSION_THRESHOLD && !isBinaryFile(filePath);
    }
    
    public void compressFile(Path sourceFile, String targetFilePath) throws IOException {
        Path targetPath = Paths.get(targetFilePath);
        Files.createDirectories(targetPath.getParent());
        
        try (FileInputStream fis = new FileInputStream(sourceFile.toFile());
             FileOutputStream fos = new FileOutputStream(targetFilePath);
             GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(fos)) {
            
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                gcos.write(buffer, 0, bytesRead);
            }
        }
    }
    
    public void decompressFile(String sourceFilePath, String targetFilePath) throws IOException {
        Path targetPath = Paths.get(targetFilePath);
        Files.createDirectories(targetPath.getParent());
        
        try (FileInputStream fis = new FileInputStream(sourceFilePath);
             GzipCompressorInputStream gcis = new GzipCompressorInputStream(fis);
             FileOutputStream fos = new FileOutputStream(targetFilePath)) {
            
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            
            while ((bytesRead = gcis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
    
    public long getCompressionRatio(Path originalFile, Path compressedFile) throws IOException {
        if (!Files.exists(originalFile) || !Files.exists(compressedFile)) {
            return 0;
        }
        
        long originalSize = Files.size(originalFile);
        long compressedSize = Files.size(compressedFile);
        
        if (originalSize == 0) {
            return 0;
        }
        
        return ((originalSize - compressedSize) * 100) / originalSize;
    }
    
    private boolean isBinaryFile(Path filePath) throws IOException {
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            return false;
        }
        
        long fileSize = Files.size(filePath);
        if (fileSize == 0) {
            return false;
        }
        
        int bytesToCheck = (int) Math.min(8192, fileSize);
        byte[] buffer = new byte[bytesToCheck];
        
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            int bytesRead = inputStream.read(buffer);
            
            for (int i = 0; i < bytesRead; i++) {
                if (buffer[i] == 0) {
                    return true;
                }
            }
        }
        
        return false;
    }
}