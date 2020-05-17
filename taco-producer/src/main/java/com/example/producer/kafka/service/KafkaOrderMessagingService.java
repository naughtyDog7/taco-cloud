package com.example.producer.kafka.service;

import com.example.producer.model.Order;
import com.example.producer.service.OrderMessagingService;
import org.springframework.kafka.core.KafkaTemplate;

//@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

//    @Autowired
    public KafkaOrderMessagingService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Override
    public void sendOrder(Order order) {
        kafkaTemplate.sendDefault("testKey", order);
    }
}
