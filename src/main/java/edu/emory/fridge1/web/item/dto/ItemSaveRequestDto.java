package edu.emory.fridge1.web.item.dto;

import edu.emory.fridge1.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ItemSaveRequestDto {
    private String itemName;
    private Integer quantity;
    private LocalDate expirationDate;

    public ItemSaveRequestDto(String itemName, Integer quantity, LocalDate expirationDate) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public Item toEntity() {
        return new Item(this.itemName, this.quantity, this.expirationDate);
    }
}
