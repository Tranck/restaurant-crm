package com.achernyadvki.restaurant.form;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemForm {
    Long id;
    String name;
    String description;
    Long category;
    Integer weight;
    String picture;
    BigDecimal price;
}
