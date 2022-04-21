package edu.emory.fridge1.domain.item;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private Integer quantity;

    private LocalDate expirationDate;

    public Item() {

    }

    public Item(String itemName, Integer quantity, LocalDate expirationDate) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public void update(String itemName, Integer quantity, LocalDate expirationDate) {
        this.itemName = itemName;
        this.quantity =quantity;
        this.expirationDate = expirationDate;
    }
}
