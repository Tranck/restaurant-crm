package com.achernyadvki.restaurant.controller;

import com.achernyadvki.restaurant.form.RoleForm;
import com.achernyadvki.restaurant.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(RoleController.ROOT_MAPPING)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    public final static String ROOT_MAPPING = "role";

    RoleService roleService;

    @GetMapping("create")
    public String createRole(Model model) {
        return "role/create";
    }

    @PostMapping("create")
    public String createRole(Model model, @ModelAttribute RoleForm roleForm) {
        roleService.createRole(model, roleForm);
        return "redirect:/";
    }

    @GetMapping("{id}")
    public String deleteRole(Model model, @PathVariable Long id) {
        roleService.deleteRole(id);
        return "redirect:/";
    }
}
