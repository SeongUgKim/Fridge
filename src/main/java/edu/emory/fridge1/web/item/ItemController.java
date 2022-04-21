package edu.emory.fridge1.web.item;

import edu.emory.fridge1.domain.item.Item;
import edu.emory.fridge1.service.ItemService;
import edu.emory.fridge1.web.item.form.ItemSaveForm;
import edu.emory.fridge1.web.item.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping
    public String items(Model model) {
        model.addAttribute("items", itemService.findAllDesc());
        return "items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemService.findById(itemId));
        return "items/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "items/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("{}", form.getItemName());
        log.info("{}", form.getQuantity());
        log.info("{}", form.getExpirationDate());
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/addForm";
        }
        Item item = new Item(form.getItemName(), form.getQuantity(), form.getExpirationDate());
        Long savedId = itemService.save(item);
        redirectAttributes.addAttribute("itemId", savedId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemService.findById(itemId));
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item")ItemUpdateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "items/editForm";
        }
        Item item = new Item(form.getItemName(), form.getQuantity(), form.getExpirationDate());
        itemService.update(itemId, item);
        return "redirect:/items/{itemId}";
    }

    @DeleteMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId) {
        itemService.delete(itemId);
        return "redirect:/";
    }

}
