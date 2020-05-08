package com.example.tacoapi.model.link_model.links;

import com.example.tacoapi.model.Ingredient;
import com.example.tacoapi.model.Ingredient.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@EqualsAndHashCode(callSuper = true)
@Data
@Relation(value = "ingredient", collectionRelation = "ingredients")
public class IngredientModel extends RepresentationModel<IngredientModel> {
    private final String name;
    private final Type type;

    public IngredientModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
