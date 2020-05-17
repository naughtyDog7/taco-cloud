package com.example.consumer.jms.service;

import com.example.producer.model.Order;
import org.springframework.jms.core.JmsTemplate;

//@Component
public class JMSOrderConsumer implements OrderConsumer {
    private final JmsTemplate jms;

//    @Autowired
    public JMSOrderConsumer(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public Order receiveOrder() {
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }
}
