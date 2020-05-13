package com.example.tacoapi.dao;

import com.example.tacoapi.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(value = "http://localhost:8089", methods = {GET})
@RepositoryRestResource
public interface IngredientDAO extends CrudRepository<Ingredient, String> {
//    List<Ingredient> findAll();
//    Ingredient findOne(String id);
//    void save(Ingredient ingredient);
}
