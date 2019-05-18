package com.ztech.codechallenge.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class JsonUtils {

    public static <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    public static String mapToJson(Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private JsonUtils() {
    }
}
