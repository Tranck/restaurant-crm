package com.achernyadvki.restaurant.service;

import com.achernyadvki.restaurant.entity.Item;
import com.achernyadvki.restaurant.form.ItemForm;
import com.achernyadvki.restaurant.repository.ItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemService {

    ItemRepository itemRepository;
    ItemCategoryService itemCategoryService;

    public void createItem(ItemForm itemForm) {
        Item item = Item.builder()
                .id(itemForm.getId())
                .name(itemForm.getName())
                .description(itemForm.getDescription())
                .category(itemCategoryService.getItemCategoryAsEntity(itemForm.getCategory()))
                .weight(itemForm.getWeight())
                .picture(itemForm.getPicture())
                .price(itemForm.getPrice())
                .build();

        itemRepository.save(item);
    }

    public Item getItemAsEntity(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Item with id %d is not found", id)
                ));
    }

    public ItemForm getItemAsForm(Long id) {
        Item item = getItemAsEntity(id);
        ItemForm itemForm = ItemForm.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .category(item.getCategory().getId())
                .weight(item.getWeight())
                .picture(item.getPicture())
                .price(item.getPrice())
                .build();

        return itemForm;
    }

    public void updateItem(ItemForm itemForm) {
        Item item = getItemAsEntity(itemForm.getId());

        item.setName(itemForm.getName());
        item.setDescription(itemForm.getDescription());
        item.setCategory(itemCategoryService.getItemCategoryAsEntity(itemForm.getCategory()));
        item.setWeight(itemForm.getWeight());
        item.setPicture(itemForm.getPicture());
        item.setPrice(itemForm.getPrice());

        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
