package com.example.tacoapi.service;

import com.example.tacoapi.model.Taco;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TacoService {
    List<Taco> findAll();

    List<Taco> findAll(Pageable pageable);

    Optional<Taco> findOne(long id);

    void save(Taco taco);

    void delete(Taco taco);
}
