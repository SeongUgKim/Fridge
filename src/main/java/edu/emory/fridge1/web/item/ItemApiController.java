package edu.emory.fridge1.web.item;

import edu.emory.fridge1.domain.item.Item;
import edu.emory.fridge1.service.ItemService;
import edu.emory.fridge1.web.item.dto.ItemSaveRequestDto;
import edu.emory.fridge1.web.item.form.ItemSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemApiController {
    private final ItemService itemService;

//    @PostMapping("/api/items")
//    public Long save(@RequestBody ItemSaveRequestDto requestDto) {
//        return itemService.save(requestDto.toEntity());
//    }

    @GetMapping("/api/items")
    public List<Item> findAll() {
        return itemService.findAllDesc();
    }
}
