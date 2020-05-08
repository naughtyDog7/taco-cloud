package com.example.tacoapi.service;

import com.example.tacoapi.dao.TacoDAO;
import com.example.tacoapi.model.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TacoServiceImpl implements TacoService {
    private final TacoDAO tacoDAO;

    @Autowired
    public TacoServiceImpl(TacoDAO tacoDAO) {
        this.tacoDAO = tacoDAO;
    }

    @Override
    public List<Taco> findAll() {
        List<Taco> tacos = new ArrayList<>();
        tacoDAO.findAll().forEach(tacos::add);
        return tacos;
    }

    @Override
    public List<Taco> findAll(Pageable pageable) {
        return tacoDAO.findAll(pageable);
    }

    @Override
    public Optional<Taco> findOne(long id) {
        return tacoDAO.findById(id);
    }

    @Override
    public void save(Taco taco) {
        tacoDAO.save(taco);
    }

    @Override
    public void delete(Taco taco) {
        tacoDAO.delete(taco);
    }
}
