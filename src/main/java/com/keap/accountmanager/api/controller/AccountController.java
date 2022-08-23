package com.keap.accountmanager.api.controller;

import com.keap.accountmanager.api.command.CreateAccountCommand;
import com.keap.accountmanager.api.command.EditAccountCommand;
import com.keap.accountmanager.api.dto.AccountDto;
import com.keap.accountmanager.api.dto.trasnformer.AccountTransformer;
import com.keap.accountmanager.data.modal.Account;
import com.keap.accountmanager.service.AccountService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountCommand createAccountCommand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountTransformer.toAccountDto(accountService.createAccount(createAccountCommand)));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String id) {
        Optional<Account> account = accountService.getAccount(id);
        if(account.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(AccountTransformer.toAccountDto(account.get()));
    }

    @GetMapping("/account")
    public ResponseEntity<List<AccountDto>> getAccountByCompanyAndName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String company) {
        List<Account> accounts;

        if(name == null) {
            accounts = accountService.getAccountByCompany(company);
        } else if(company == null) {
            accounts = accountService.getAccountByName(name);
        } else {
            accounts = accountService.getAccountByUsernameAndAndCompany(name, company);
        }

        return ResponseEntity.status(HttpStatus.OK).body(accounts.stream().map(AccountTransformer::toAccountDto).collect(Collectors.toList()));
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<AccountDto> editAccountById(
            @PathVariable String id,
            @RequestBody EditAccountCommand editAccountCommand) {
        Optional<Account> account = accountService.getAccount(id);

        if(account.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(AccountTransformer.toAccountDto(accountService.editAccount(account.get(), editAccountCommand)));
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
