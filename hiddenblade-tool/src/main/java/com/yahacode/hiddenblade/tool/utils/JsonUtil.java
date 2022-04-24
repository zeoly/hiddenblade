package com.yahacode.hiddenblade.tool.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, ToStringSerializer.instance);
            mapper.registerModule(javaTimeModule);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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

    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        try {
            JavaType javaType = getMapper().getTypeFactory().constructParametricType(ArrayList.class, clazz);
            return getMapper().readValue(jsonString, javaType);
        } catch (JsonProcessingException e) {
            log.warn("String to list fail, String: {}, type: {}", jsonString, clazz.getSimpleName(), e);
            return null;
        }
    }

    public static JsonNode toJsonNode(String jsonString) {
        try {
            return getMapper().readTree(jsonString);
        } catch (Exception e) {
            log.warn("String to node fail, String: {}", jsonString, e);
            return null;
        }
    }

    public static JsonNode objToJsonNode(Object obj) {
        try {
            return toJsonNode(toStr(obj));
        } catch (Exception e) {
            log.warn("obj to node fail, obj: {}", obj.toString(), e);
            return null;
        }
    }

    public static List<JsonNode> toJsonNodeList(String jsonString) {
        JsonNode node = toJsonNode(jsonString);
        if (node != null && node.isArray()) {
            List<JsonNode> list = new ArrayList<>();
            Iterator<JsonNode> iterator = node.elements();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
            return list;
        } else {
            log.warn("String to node list fail, String: {}", jsonString);
            return null;
        }
    }
}
