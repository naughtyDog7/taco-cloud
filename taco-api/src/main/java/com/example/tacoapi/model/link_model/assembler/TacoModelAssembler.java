package com.example.tacoapi.model.link_model.assembler;

import com.example.tacoapi.controller.DesignTacoController;
import com.example.tacoapi.model.Taco;
import com.example.tacoapi.model.link_model.links.TacoModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {
    public TacoModelAssembler() {
        super(DesignTacoController.class, TacoModel.class);
    }

    @Override
    protected TacoModel instantiateModel(Taco entity) {
        return new TacoModel(entity);
    }


    @Override
    public TacoModel toModel(Taco entity) {
        return createModelWithId(entity.getId(), entity);
    }
}
