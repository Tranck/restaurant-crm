package com.achernyadvki.restaurant.service;

import com.achernyadvki.restaurant.entity.ItemCategory;
import com.achernyadvki.restaurant.form.ItemCategoryForm;
import com.achernyadvki.restaurant.repository.ItemCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemCategoryService {

    ItemCategoryRepository itemCategoryRepository;

    public List<ItemCategoryForm> getAll() {
        List<ItemCategoryForm> items = new ArrayList<>();

        itemCategoryRepository.findAll().forEach(entity -> items.add(ItemCategoryForm.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build()));

        return items;
    }

    public ItemCategoryForm getItemCategoryAsForm(Long id) {
        ItemCategory itemCategory = getItemCategoryAsEntity(id);
        return ItemCategoryForm.builder()
                .id(itemCategory.getId())
                .name(itemCategory.getName())
                .build();
    }

    public ItemCategory getItemCategoryAsEntity(Long id) {
        return itemCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Item category with id %d is not found", id)));
    }

    public void createItemCategory(ItemCategoryForm itemCategoryForm) {
        ItemCategory entity = ItemCategory.builder()
                .name(itemCategoryForm.getName())
                .build();

        itemCategoryRepository.save(entity);
    }

    public void updateItemCategory(ItemCategoryForm itemCategoryForm) {
       ItemCategory entity = getItemCategoryAsEntity(itemCategoryForm.getId());
       entity.setName(itemCategoryForm.getName());
       itemCategoryRepository.save(entity);
    }

    public void deleteItemCategory(Long id) {
        itemCategoryRepository.deleteById(id);
    }
}
