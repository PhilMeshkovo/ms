package com.blinov.producer.configuration;

import com.blinov.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@NoArgsConstructor
@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, Data> kafkaTemplate;
    @Value(value = "${kafka.topic.name}")
    private String topicName;


    public void sendMessage(final Data data) {
        CompletableFuture<SendResult<String, Data>> future =  kafkaTemplate.send(topicName, data);

        future.thenAccept(result -> {
            System.out.println(String.format("Sent Message = = {%s} with offset = {%s}", data, result));
        }).exceptionally(ex -> {
            System.out.println(String.format("Unable to send message = {%s} dut to: {%s}", data, ex.getMessage()));
            return null; // Возвращаем null, чтобы продолжить цепочку обработки ошибок
        });
    }
}
