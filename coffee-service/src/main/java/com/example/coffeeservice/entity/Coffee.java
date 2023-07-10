package com.example.coffeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "coffees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coffee_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "ingredient_list",
            joinColumns = @JoinColumn(name = "coffee_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )

    private Set<Ingredient> ingredients;
}
