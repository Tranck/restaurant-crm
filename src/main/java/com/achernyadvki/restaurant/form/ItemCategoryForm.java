package com.achernyadvki.restaurant.form;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCategoryForm {
    Long id;

    @Size(min = 4, max = 255, message = "Length of this field must be from 4 to 255")
    @NotEmpty(message = "This field must be filled")
    String name;

}
