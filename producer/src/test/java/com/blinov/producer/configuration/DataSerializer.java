package com.blinov.producer.configuration;

import com.blinov.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

public class DataSerializer implements Serializer<Data>, Deserializer<Data> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Data data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (IOException e) {
            throw new RuntimeException("Error serializing Data", e);
        }
    }

    @Override
    public Data deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Data.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing Data", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Ничего не делаем
    }

    @Override
    public void close() {
        // Ничего не делаем
    }
}
