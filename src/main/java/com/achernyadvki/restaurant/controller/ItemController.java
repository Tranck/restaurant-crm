package com.achernyadvki.restaurant.controller;

import com.achernyadvki.restaurant.form.ItemForm;
import com.achernyadvki.restaurant.service.ItemService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(ItemController.ROOT_MAPPING)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemController {

    public static final String ROOT_MAPPING = "item";
    ItemService itemService;

    /*

        TODO: 1. Реализовать возможность создавать элементы меню (localhost:8080/item/create)
        TODO: 2. Реализовать возможность просматривать элементы меню (localhost:8080/item/{id})
        TODO: 3. Реализовать возможность редактировать элементы меню (localhost:8080/item/{id}/edit)
        TODO: 4. Реализовать возможность удалять элементы меню (localhost:8080/item/{id}/remove)

        TODO: Вывод элемента меню организовать по такому же принципу как и для категории меню
        TODO: Работа с категорией пока что просто как input type="number"

     */

    @GetMapping("{id}")
    public String getItem(Model model, @PathVariable Long id) {
        model.addAttribute("item", itemService.getItemAsForm(id));
        return "item/view";
    }

    @GetMapping("create")
    public String createItem(Model model) {
        return "item/create";
    }

    @PostMapping("create")
    public String createItem(Model model, @ModelAttribute ItemForm itemForm) {
        itemService.createItem(itemForm);
        return "redirect:/";
    }

    @GetMapping("{id}/edit")
    public String editItem(Model model, @PathVariable Long id) {
        model.addAttribute("item", itemService.getItemAsForm(id));
        return "item/edit";
    }

    @PostMapping("{id}/edit")
    public String editItem(Model model, @PathVariable Long id, @ModelAttribute ItemForm itemForm) {
        itemService.updateItem(itemForm);
        return "redirect:/";
    }

    @GetMapping("{id}/delete")
    public String deleteItem(Model model, @PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/";
    }
}
