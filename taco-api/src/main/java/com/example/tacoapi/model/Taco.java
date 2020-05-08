package com.example.tacoapi.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tacos")
@RestResource(rel = "tacos", path = "tacos")
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @NotNull(message = "Please choose items (min 1)")
    @Size(min = 1, message = "At least one item should be chosen")

    @Transient
    private List<String> ingredients;

    @ToString.Exclude
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredientsList;

    private LocalDateTime createdAt;

    @PrePersist
    void createdAt() {
        createdAt = LocalDateTime.now();
    }

    public double totalPrice() {
        return Double.parseDouble(
                String.format("%.2f",
                        ingredientsList
                                .stream()
                                .mapToDouble(Ingredient::getPrice)
                                .sum()));
    }
}
