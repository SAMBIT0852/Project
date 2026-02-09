package com.cosmos.vcs.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtil {
    
    private static final ObjectMapper objectMapper;
    
    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    
    public static <T> void writeToFile(T object, File file) throws IOException {
        file.getParentFile().mkdirs();
        objectMapper.writeValue(file, object);
    }
    
    public static <T> T readFromFile(File file, Class<T> clazz) throws IOException {
        return objectMapper.readValue(file, clazz);
    }
    
    public static <T> List<T> readListFromFile(File file, Class<T> clazz) throws IOException {
        return objectMapper.readValue(file, objectMapper.getTypeFactory()
            .constructCollectionType(List.class, clazz));
    }
    
    public static <T> String toJsonString(T object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }
    
    public static <T> T fromJsonString(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }
    
    public static <T> T fromJsonString(String json, TypeReference<T> typeRef) throws IOException {
        return objectMapper.readValue(json, typeRef);
    }
}