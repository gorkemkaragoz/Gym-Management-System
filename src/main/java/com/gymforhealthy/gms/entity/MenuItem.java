package com.gymforhealthy.gms.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.PositiveOrZero;
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
    @PositiveOrZero
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonBackReference
    private FoodDrinkType type;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemAllergen> allergens;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemIngredient> ingredients;

}