package com.example.tacoapi.dao;

import com.example.tacoapi.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(value = "http://localhost:8089")
@RepositoryRestResource
public interface OrderDAO extends CrudRepository<Order, Long> {
//    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
//    Order findTopByUserAndOrderedFalseOrderByPlacedAtDesc(User user);
//    List<Order> findAll();
//    Order findOne(int id);
//    void save(Order order);
//    void update(Order order);
//    void delete(Order order);
}
