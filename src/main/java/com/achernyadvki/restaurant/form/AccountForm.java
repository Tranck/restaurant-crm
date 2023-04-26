package com.achernyadvki.restaurant.form;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountForm {
    Long id;

    @Size(min = 4, max = 255, message = "Length of this field must be from 4 to 255")
    @NotEmpty(message = "This field must be filled")
    String username;

    @Size(min = 5, max = 255, message = "Length of this field must be from 5 to 255")
    @NotEmpty(message = "This field must be filled")
    String password;

    @NotNull(message = "This field must be filled")
    Long role;
}
