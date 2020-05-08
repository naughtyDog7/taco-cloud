package com.example.tacoapi.model.link_model.assembler;

import com.example.tacoapi.controller.IngredientController;
import com.example.tacoapi.model.Ingredient;
import com.example.tacoapi.model.link_model.links.IngredientModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {

    public IngredientModelAssembler() {
        super(IngredientController.class, IngredientModel.class);
    }

    @Override
    protected IngredientModel instantiateModel(Ingredient entity) {
        return new IngredientModel(entity);
    }

    @Override
    public IngredientModel toModel(Ingredient entity) {
        return createModelWithId(entity.getId(), entity);
    }
}
