package io.hasenpower.hpchat.model.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Clone {

    private static final Logger LOGGER = LoggerFactory.getLogger(Clone.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T of(T t, Class<T> typeParameter) {
        try {
            String json = OBJECT_MAPPER.writeValueAsString(t);
            return OBJECT_MAPPER.readValue(json, typeParameter);
        } catch (java.io.IOException e) {
            LOGGER.error("Could not clone object.", e);
        }

        return null;
    }

    public static <T> T of(T t, JavaType javaType) {
        try {
            String json = OBJECT_MAPPER.writeValueAsString(t);
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (java.io.IOException e) {
            LOGGER.error("Could not clone object.", e);
        }

        return null;
    }
}
