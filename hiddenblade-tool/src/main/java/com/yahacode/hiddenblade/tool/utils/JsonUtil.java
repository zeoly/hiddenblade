package com.yahacode.hiddenblade.tool.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * json format utilities
 *
 * @author zengyongli
 * @since 2022/04/13
 */
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper;

    private static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    public static String toStr(Object object) {
        try {
            return getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn("object to String fail, object: {}", object.toString(), e);
            return null;
        }
    }

    public static <T> T toObj(String jsonString, Class<T> clazz) {
        try {
            return getMapper().readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            log.warn("String to object fail, String: " + jsonString + ", type: " + clazz.getSimpleName(), e);
            return null;
        }
    }
}
