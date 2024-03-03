package com.blinov.producer;

import com.blinov.producer.configuration.MessageProducer;
import com.blinov.producer.service.ApiService;
import com.blinov.producer.service.ApiServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ProducerApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        ApiService service = context.getBean(ApiServiceImpl.class);

        service.getData(3).forEach(producer::sendMessage);
    }
}
