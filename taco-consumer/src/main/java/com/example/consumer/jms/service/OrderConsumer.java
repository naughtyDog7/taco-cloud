package com.example.consumer.jms.service;


import com.example.producer.model.Order;

import javax.jms.JMSException;

public interface OrderConsumer {
    Order receiveOrder() throws JMSException;
}
