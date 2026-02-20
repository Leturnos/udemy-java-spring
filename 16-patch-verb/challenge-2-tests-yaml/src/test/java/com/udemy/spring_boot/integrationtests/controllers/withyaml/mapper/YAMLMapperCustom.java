package com.udemy.spring_boot.integrationtests.controllers.withyaml.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YAMLMapperCustom implements ObjectMapper {

    private final YAMLMapper yamlMapper;

    public YAMLMapperCustom() {
        this.yamlMapper = new YAMLMapper();
        this.yamlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public Object deserialize(ObjectMapperDeserializationContext context) {
        try {
            return yamlMapper.readValue(context.getDataToDeserialize().asString(), (JavaType) context.getType());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar YAML", e);
        }
    }

    @Override
    public Object serialize(ObjectMapperSerializationContext context) {
        try {
            return yamlMapper.writeValueAsString(context.getObjectToSerialize());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar para YAML", e);
        }
    }

    public <T> T readValue(String content, TypeReference<T> valueTypeRef) throws JsonProcessingException {
        return yamlMapper.readValue(content, valueTypeRef);
    }

    public <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException {
        return yamlMapper.readValue(content, valueType);
    }
}
