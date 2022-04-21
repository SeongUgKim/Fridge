package edu.emory.fridge1.service;

import edu.emory.fridge1.domain.item.Item;
import edu.emory.fridge1.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long save(Item item) {
        return itemRepository.save(item).getId();
    }

    @Transactional
    public Long update(Long id, Item item) {
        Item findItem = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Item"));
        findItem.update(item.getItemName(), item.getQuantity(), item.getExpirationDate());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Item findItem = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Item"));
        itemRepository.delete(findItem);
    }

    @Transactional(readOnly = true)
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Item"));
    }

    @Transactional(readOnly = true)
    public List<Item> findAllDesc() {
        return itemRepository.findAllAsc();
    }
}
