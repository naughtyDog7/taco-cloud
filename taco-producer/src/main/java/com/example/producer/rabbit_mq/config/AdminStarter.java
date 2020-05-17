package com.example.producer.rabbit_mq.config;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AdminStarter implements ApplicationListener<ApplicationReadyEvent> {

    private final RabbitAdmin rabbitAdmin;

    @Autowired
    public AdminStarter(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        rabbitAdmin.initialize();
    }
}
