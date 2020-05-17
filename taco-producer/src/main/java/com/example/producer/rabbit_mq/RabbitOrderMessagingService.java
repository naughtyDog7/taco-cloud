package com.example.producer.rabbit_mq;

import com.example.producer.model.Order;
import com.example.producer.service.OrderMessagingService;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend("tacocloud.order.queue", order, messageWebHeader);
    }

    public static final MessagePostProcessor messageWebHeader =
            message -> {
                message.getMessageProperties().setHeader("X_ORDER_SOURCE", "WEB");
                return message;
            };
}
