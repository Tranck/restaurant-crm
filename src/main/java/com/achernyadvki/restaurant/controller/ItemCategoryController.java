package com.achernyadvki.restaurant.controller;

import com.achernyadvki.restaurant.form.ItemCategoryForm;
import com.achernyadvki.restaurant.form.validator.ItemCategoryFormValidator;
import com.achernyadvki.restaurant.service.ItemCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping(ItemCategoryController.ROOT_MAP)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemCategoryController {

    public static final String ROOT_MAP = "item_category";

    ItemCategoryFormValidator itemCategoryFormValidator;
    ItemCategoryService itemCategoryService;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("itemcategory", itemCategoryService.getAll());
        return "itemcategory/list";
    }

    @GetMapping("{id}")
    public String getItemCategory(Model model, @PathVariable Long id) {
        model.addAttribute("itemcategory", itemCategoryService.getItemCategoryAsForm(id));
        return "itemcategory/view";
    }

    @GetMapping("create")
    public String createItemCategory(Model model) {
        model.addAttribute("itemcategory", new ItemCategoryForm());
        return "itemcategory/create";
    }

    @PostMapping("create")
    public String createItemCategory(Model model, @Valid @ModelAttribute("itemcategory") ItemCategoryForm itemCategoryForm ,
                                     Errors errors) {
        itemCategoryFormValidator.validate(itemCategoryForm, errors);

        if (errors.hasErrors()) {
            model.addAttribute("itemcategory", itemCategoryForm);
            return "itemcategory/create";
        }

        itemCategoryService.createItemCategory(itemCategoryForm);
        return "redirect:/";
    }

    @GetMapping("{id}/edit")
    public String editItemCategory(Model model, @PathVariable Long id) {
        model.addAttribute("itemcategory", itemCategoryService.getItemCategoryAsForm(id));
        return "itemcategory/edit";
    }

    @PostMapping("{id}/edit")
    public String editItemCategory(Model model, @Valid @ModelAttribute ("itemcategory") ItemCategoryForm itemCategoryForm,
                                   Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("itemcategory", itemCategoryForm);
            return "itemcategory/edit";
        }

        itemCategoryService.updateItemCategory(itemCategoryForm);
        return "redirect:/";
    }

    @GetMapping("{id}/delete")
    public String deleteItemCategory(Model model, @PathVariable Long id) {
        itemCategoryService.deleteItemCategory(id);
        return "redirect:/";
    }
}
