package com.example.producer.service;

import com.example.producer.model.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
