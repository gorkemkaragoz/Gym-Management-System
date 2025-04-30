package com.gymforhealthy.gms.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonBackReference
    private FoodDrinkType type;

    @JsonManagedReference
    @OneToMany(mappedBy = "item")
    private List<ItemAllergen> allergens;

    @JsonManagedReference
    @OneToMany(mappedBy = "item")
    private List<ItemIngredient> ingredients;
}