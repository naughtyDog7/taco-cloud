package com.example.mail.config;

import com.example.mail.model.Ingredient;
import com.example.mail.props.ApiProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class IngredientsInitializerConfig {

    @Bean("ingredients")
    public Map<String, Ingredient> ingredientsFromApi(RestTemplate restTemplate, ApiProperties apiProperties) {
        ResponseEntity<Ingredient[]> ingredientsResponse = restTemplate.getForEntity(apiProperties.getIngredientUrl(), Ingredient[].class);
        return Arrays.stream(Optional.ofNullable(ingredientsResponse.getBody())
                .orElseThrow()).collect(Collectors.toMap(Ingredient::getName, Function.identity()));
    }
}
