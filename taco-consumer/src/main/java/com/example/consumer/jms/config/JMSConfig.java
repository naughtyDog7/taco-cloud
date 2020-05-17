package com.example.consumer.jms.config;

import com.example.producer.model.Order;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.Map;

//@Configuration
//@EnableScheduling
public class JMSConfig {
//    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_typeId");
        converter.setTypeIdMappings(Map.of("order", Order.class));
        return converter;
    }

}
