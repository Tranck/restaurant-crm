package com.achernyadvki.restaurant.controller;

import com.achernyadvki.restaurant.form.AccountForm;
import com.achernyadvki.restaurant.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        model.addAttribute("account", new AccountForm());
        return "account/create";
    }

    @PostMapping("create")
    public String createAccount(Model model, @Valid @ModelAttribute("account") AccountForm accountForm,
                                Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("account", accountForm);
            return "account/create";
        }

        accountService.createAccount(accountForm);
        return "redirect:/";
    }

    @GetMapping("{id}/edit")
    public String editAccount(Model model, @PathVariable Long id) {
        model.addAttribute("account", accountService.getAccountAsForm(id));
        return "account/edit";
    }

    @PostMapping("{id}/edit")
    public String editAccount(@PathVariable Long id, @Valid @ModelAttribute AccountForm accountForm, Errors errors,
                              Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("account", accountForm);
            return "account/edit";
        }

        accountService.editAccount(accountForm);
        return "redirect:/";
    }

    @GetMapping("{id}/delete")
    public String deleteAccount(Model model, @PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/";
    }
}
