package com.example.tacoapi.config;

import com.example.tacoapi.model.Taco;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;

@Configuration
public class RestConfig {

    @Bean
    public RepresentationModelProcessor<CollectionModel<Taco>> tacoProcessor(EntityLinks links) {
        //do not replace with lambda or rest repository wont work
        return new RepresentationModelProcessor<CollectionModel<Taco>>() {
            @Override
            public CollectionModel<Taco> process(CollectionModel<Taco> model) {
                model.add(
                        links.linkFor(Taco.class)
                                .slash("recent")
                                .withRel("recents"));
                return model;
            }
        };
    }

}
