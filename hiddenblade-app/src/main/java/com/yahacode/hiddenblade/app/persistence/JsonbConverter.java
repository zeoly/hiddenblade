package com.yahacode.hiddenblade.app.persistence;

import com.fasterxml.jackson.databind.JsonNode;
import com.yahacode.hiddenblade.tool.utils.JsonUtil;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.sql.SQLException;

/**
 * Postgresql jsonb type to JsonNode type converter
 *
 * @author zengyongli
 * @since 2021/04/23
 */
public class JsonbConverter implements AttributeConverter<JsonNode, Object> {

    private static final Logger log = LoggerFactory.getLogger(JsonbConverter.class);

    @Override
    public Object convertToDatabaseColumn(JsonNode attribute) {
        PGobject object = new PGobject();
        try {
            object.setType("jsonb");
            object.setValue(JsonUtil.toStr(attribute));
        } catch (SQLException e) {
            log.error("convert to db column error: {}", JsonUtil.toStr(attribute), e);
        }
        return object;
    }

    @Override
    public JsonNode convertToEntityAttribute(Object dbData) {
        if (dbData instanceof PGobject) {
            if (((PGobject) dbData).getType().equals("jsonb")) {
                return JsonUtil.toJsonNode(((PGobject) dbData).getValue());
            } else {
                log.error("error pg type: {}", ((PGobject) dbData).getType());
            }
        }
        if (dbData != null) {
            log.error("error pg object: {}", JsonUtil.toStr(dbData));
        }
        return JsonUtil.toJsonNode("");
    }
}
