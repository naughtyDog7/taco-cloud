package com.example.tacoapi.controller;

import com.example.tacoapi.model.Taco;
import com.example.tacoapi.model.link_model.assembler.TacoModelAssembler;
import com.example.tacoapi.model.link_model.links.TacoModel;
import com.example.tacoapi.property.OrderProps;
import com.example.tacoapi.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
//http://localhost:8089
@CrossOrigin("*")
@RequestMapping(path = "/design", produces = {"application/json", "text/xml"})
public class DesignTacoController {
    private final TacoService tacoService;
    private final OrderProps orderProps;

    @Autowired
    public DesignTacoController(TacoService tacoService, OrderProps orderProps) {
        this.tacoService = tacoService;
        this.orderProps = orderProps;
    }

    @GetMapping("/recent")
    public CollectionModel<TacoModel> recentTacos() {
        List<Taco> tacos = tacoService.findAll(
                PageRequest.of(0, orderProps.getOrdersListLength(),
                        Sort.by("createdAt").descending()));
        CollectionModel<TacoModel> tacoModels =
                new TacoModelAssembler().toCollectionModel(tacos);
        tacoModels.add(
                linkTo(methodOn(DesignTacoController.class).recentTacos())
                        .withRel("recents"));
        return tacoModels;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Taco getById(@PathVariable Long id) {
        return tacoService.findOne(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTaco(@RequestBody Taco taco) {
        tacoService.save(Optional.ofNullable(taco)
                .orElseThrow(BadRequestException::new));
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateTaco(@RequestBody Taco taco) {
        Optional.ofNullable(taco).orElseThrow(BadRequestException::new);
        tacoService.findOne(taco.getId()).orElseThrow(ResourceNotFoundException::new);
        tacoService.save(taco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaco(@PathVariable Long id) {
        tacoService.delete(tacoService.findOne(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class BadRequestException extends RuntimeException {
    }
}
