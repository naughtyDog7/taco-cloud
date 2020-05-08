package com.example.tacoapi.service;

import com.example.tacoapi.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findOne(long id);

    void save(Order order);

    void delete(Order order);

//    List<Order> findOrdersByUserId(User user, Pageable pageable);
//
//    Optional<Order> findCurrent(User user);
}
