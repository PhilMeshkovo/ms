package com.blinov.producer.configuration;

import com.blinov.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.lang.reflect.Field;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageProducerTest {

    @Mock
    private KafkaTemplate<String, Data> kafkaTemplate;
    @Mock
    private CompletableFuture<SendResult<String, Data>> futureMock;
    @Mock
    private CompletableFuture<Void> voidCompletableFuture;
    @Mock
    private SendResult sendResult;
    @InjectMocks
    private MessageProducer messageProducer;

    @Test
    void sendMessageTest() throws NoSuchFieldException, IllegalAccessException {
        Data data = new Data("Phil-11", "111@mail.ru", "123");

        Field field = MessageProducer.class.getDeclaredField("topicName");
        field.setAccessible(true);
        field.set(messageProducer, "topic1");

        when(kafkaTemplate.send(eq("topic1"), any(Data.class))).thenReturn(futureMock);
        Mockito.when(futureMock.thenAccept(Mockito.any())).thenAnswer(invocation -> {
            Consumer<SendResult<String, Data>> consumer = invocation.getArgument(0);
            consumer.accept(sendResult);
            return voidCompletableFuture;
        });
        Assertions.assertDoesNotThrow(() -> messageProducer.sendMessage(data));
        Mockito.verify(kafkaTemplate).send(eq("topic1"), eq(data));
    }
}