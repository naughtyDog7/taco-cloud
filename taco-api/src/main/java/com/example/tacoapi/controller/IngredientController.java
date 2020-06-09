package com.example.tacoapi.controller;

import com.example.tacoapi.model.Ingredient;
import com.example.tacoapi.service.IngredientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RepositoryRestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("idNames")
    public ResponseEntity<List<SimpleIngredient>> getSimpleIngredients() {
        return new ResponseEntity<>(this.ingredientService.findAll().stream()
                .map(SimpleIngredient::fromIngredient)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    private static class SimpleIngredient {
        private String id;
        private String name;

        public static SimpleIngredient fromIngredient(Ingredient ingredient) {
            return new SimpleIngredient(ingredient.getId(), ingredient.getName());
        }
    }
}
