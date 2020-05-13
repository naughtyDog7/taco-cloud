package com.example.tacoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @Column(length = 4)
    private final String id;

    @Column(length = 25)
    private final String name;

    @Convert(converter = TypeAttributeConverter.class)
    private final Type type;

    private double price;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    @Converter
    public static class TypeAttributeConverter implements AttributeConverter<Type, String> {
        @Override
        public String convertToDatabaseColumn(Type type) {
            return type.toString();
        }

        @Override
        public Type convertToEntityAttribute(String s) {
            return Type.valueOf(s);
        }
    }
}
