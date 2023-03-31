package com.achernyadvki.restaurant.controller;

import com.achernyadvki.restaurant.form.AccountForm;
import com.achernyadvki.restaurant.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(AccountController.ROOT_MAPPING)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    public static final String ROOT_MAPPING = "account";

    AccountService accountService;

    @GetMapping("{id}")
    public String getAccount(Model model, @PathVariable Long id) {
        model.addAttribute("account", accountService.getAccountAsForm(id));
        return "account/view";
    }

    @GetMapping("create")
    public String createAccount(Model model) {
        return "account/create";
    }

    @PostMapping("create")
    public String createAccount(Model model, @ModelAttribute AccountForm accountForm) {
        accountService.createAccount(accountForm);
        return "redirect:/";
    }

    @GetMapping("{id}/edit")
    public String editAccount(Model model, @PathVariable Long id) {
        model.addAttribute("account", accountService.getAccountAsForm(id));
        return "account/edit";
    }

    @PostMapping("{id}/edit")
    public String editAccount(Model model, @PathVariable Long id, @ModelAttribute AccountForm accountForm) {
        accountService.editAccount(accountForm);
        return "redirect:/";
    }

    @GetMapping("{id}/delete")
    public String deleteAccount(Model model, @PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/";
    }
}
