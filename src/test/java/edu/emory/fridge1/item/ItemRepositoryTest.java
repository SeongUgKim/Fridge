package edu.emory.fridge1.item;

import edu.emory.fridge1.domain.item.Item;
import edu.emory.fridge1.domain.item.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    public void tearDown() throws Exception {
        itemRepository.deleteAll();
    }

    @Test
    public void findItem() throws Exception {
        // given
        String name = "item1";
        Integer quantity = 10;
        LocalDate date = LocalDate.now();
        itemRepository.save(new Item(name, quantity, date));
        String name1 = "item2";
        Integer quantity1 = 20;
        LocalDate date1 = LocalDate.of(2023, 01, 01);
        itemRepository.save(new Item(name1, quantity1, date1));
        List<Item> itemList = itemRepository.findAllAsc();
        for (Item item : itemList) {
            System.out.println(item.getItemName());
        }

    }

}