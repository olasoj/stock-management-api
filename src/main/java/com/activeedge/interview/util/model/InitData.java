package com.activeedge.interview.util.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InitData {
    private static ObjectMapper sObjectMapper;
    @Autowired
    @Qualifier("jacksonObjectMapper")
    private ObjectMapper objectMapper;

    public static <M> List<M> getInitData(String modelFilePath, Class<M> classType) throws IOException {
        return sObjectMapper.readValue(getModelFile(modelFilePath), getCollectionTypeModel(classType));
    }

    private static <M> CollectionType getCollectionTypeModel(Class<M> classType) {
        return sObjectMapper.getTypeFactory().constructCollectionType(ArrayList.class, classType);
    }

    private static InputStream getModelFile(String modelFilePath) {
        return TypeReference.class.getResourceAsStream("/init" + modelFilePath);
    }

    @PostConstruct
    public void init() {
        InitData.sObjectMapper = objectMapper;
    }

}
