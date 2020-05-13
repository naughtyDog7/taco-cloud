package com.example.tacoapi.model.link_model.links;

import com.example.tacoapi.model.Taco;
import com.example.tacoapi.model.link_model.assembler.IngredientModelAssembler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoModel extends RepresentationModel<TacoModel> {

    private static final IngredientModelAssembler
            assembler = new IngredientModelAssembler();

    private final String name;
    private final LocalDateTime createdAt;
    private final CollectionModel<IngredientModel> ingredients;

    public TacoModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients =
                assembler.toCollectionModel(taco.getIngredients());
    }
}
