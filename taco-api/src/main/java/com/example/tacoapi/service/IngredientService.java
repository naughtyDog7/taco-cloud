package com.example.tacoapi.service;

import com.example.tacoapi.model.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findAll();

    Ingredient findOne(String id);

    void save(Ingredient ingredient);
}
