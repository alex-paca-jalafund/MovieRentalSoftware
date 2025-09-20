package org.movie.dataPersistence.serializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjectMapperSerializer<T> implements DataSerializer<T> {
    private final ObjectMapper mapper;

    public ObjectMapperSerializer(ObjectMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public List<T> read(File file, Class<T> type) throws IOException {
        if (!file.exists()) return new ArrayList<>();
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, type);
        return mapper.readValue(
                file, javaType);
    }

    @Override
    public void write(File file, List<T> items) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, items);
    }
}
