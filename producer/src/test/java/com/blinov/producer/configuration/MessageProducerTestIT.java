package com.blinov.producer.configuration;

import com.blinov.Data;
import com.blinov.producer.kafka.ReusableKafkaContainer;
import com.blinov.producer.kafka.ReuseKafkaContainerExtension;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.testcontainers.containers.KafkaContainer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ReuseKafkaContainerExtension.class)
public class MessageProducerTestIT {

    @ReusableKafkaContainer
    KafkaContainer kafka;

    @SneakyThrows
    @Test
    void sendRecords() {
        String bootstrapServers = kafka.getBootstrapServers();
        String topicName = "send-topic";
        KafkaTemplate<String, Data> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        MessageProducer messageProducer = new MessageProducer(kafkaTemplate, topicName);
        Data data = new Data("Phil-11", "111@mail.ru", "123");
        messageProducer.sendMessage(data);

        ConsumerService consumerService = new ConsumerService(bootstrapServers, topicName);
        assertEquals(1, consumerService.getRecordsSize());
    }

    public ProducerFactory<String, Data> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DataSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }
}
