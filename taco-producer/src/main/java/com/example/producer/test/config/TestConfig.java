package com.example.producer.test.config;

import com.example.producer.service.OrderMessagingService;
import com.example.producer.test.TestSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class TestConfig {

    @Bean
    public TestSender testSender(@Qualifier("rabbitOrderMessagingService") OrderMessagingService sender) {
        return new TestSender(sender);
    }

}
