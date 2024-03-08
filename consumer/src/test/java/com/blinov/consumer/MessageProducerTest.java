package com.blinov.consumer;

import com.blinov.Data;
import com.blinov.consumer.configuration.MessageListener;
import com.blinov.consumer.service.DataService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class MessageProducerTest {

    @Mock
    DataService dataService;

    @InjectMocks
    MessageListener messageListener;

    @SneakyThrows
    @Test
    void listenerTest() {
        Data data = new Data("Phil-11", "111@mail.ru", "123");
        messageListener.listener(data);
        verify(dataService).saveMessage(data);
    }
}
