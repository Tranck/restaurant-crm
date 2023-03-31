package com.achernyadvki.restaurant.service;

import com.achernyadvki.restaurant.entity.Account;
import com.achernyadvki.restaurant.form.AccountForm;
import com.achernyadvki.restaurant.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    PasswordEncoder passwordEncoder;
    AccountRepository accountRepository;
    RoleService roleService;

    public void createAccount(AccountForm accountForm) {
        accountRepository.save(Account.builder()
                .username(accountForm.getUsername())
                .password(passwordEncoder.encode(accountForm.getPassword()))
                .role(roleService.getRoleById(accountForm.getRole()))
                .build());
    }

    public void editAccount(AccountForm accountForm) {
        Account account = getAccount(accountForm.getId());

        account.setUsername(accountForm.getUsername());
        if (Objects.nonNull(accountForm.getPassword())) {
            account.setPassword(accountForm.getPassword());
        }
        account.setRole(roleService.getRoleById(accountForm.getRole()));

        accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account with id " + id + " does not exist"));
    }

    public AccountForm getAccountAsForm(Long id) {
        Account account = getAccount(id);
        return AccountForm.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole().getId())
                .build();
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
