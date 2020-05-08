package com.example.tacoapi.dao;

import com.example.tacoapi.model.Taco;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacoDAO extends CrudRepository<Taco, Long> {
    List<Taco> findAll(Pageable pageable);
//    Taco findOne(int id);
//    void save(Taco taco);
//    void update(Taco taco);
//    void delete(Taco taco);
}
