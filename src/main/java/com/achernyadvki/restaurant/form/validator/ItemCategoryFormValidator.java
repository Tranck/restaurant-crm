package com.achernyadvki.restaurant.form.validator;

import com.achernyadvki.restaurant.form.ItemCategoryForm;
import com.achernyadvki.restaurant.repository.ItemCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemCategoryFormValidator implements Validator {

    ItemCategoryRepository itemCategoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
       return clazz.equals(ItemCategoryForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ItemCategoryForm itemCategoryForm = (ItemCategoryForm) target;

        if (itemCategoryRepository.existsByNameLikeIgnoreCase(itemCategoryForm.getName())) {
            errors.rejectValue("name",  "", "Already exists similar category");
        }
    }
}
