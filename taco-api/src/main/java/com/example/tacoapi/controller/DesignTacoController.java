package com.example.tacoapi.controller;

import com.example.tacoapi.model.Taco;
import com.example.tacoapi.model.link_model.assembler.TacoModelAssembler;
import com.example.tacoapi.model.link_model.links.TacoModel;
import com.example.tacoapi.property.OrderProps;
import com.example.tacoapi.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
@RequestMapping("/tacos")
public class DesignTacoController {
    private final TacoService tacoService;
    private final OrderProps orderProps;

    @Autowired
    public DesignTacoController(TacoService tacoService, OrderProps orderProps) {
        this.tacoService = tacoService;
        this.orderProps = orderProps;
    }

    @GetMapping("/recent")
    public ResponseEntity<CollectionModel<TacoModel>> recentTacos() {
        List<Taco> tacos = tacoService.findAll(
                PageRequest.of(0, orderProps.getOrdersListLength(),
                        Sort.by("createdAt").descending()));
        CollectionModel<TacoModel> tacoModels =
                new TacoModelAssembler().toCollectionModel(tacos);
        tacoModels.add(
                linkTo(methodOn(DesignTacoController.class).recentTacos())
                        .withRel("recents"));
        return new ResponseEntity<>(tacoModels, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class BadRequestException extends RuntimeException {
    }
}
