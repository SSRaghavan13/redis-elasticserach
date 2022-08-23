package com.keap.accountmanager.service.impl;

import com.keap.accountmanager.api.command.CreateAccountCommand;
import com.keap.accountmanager.api.command.EditAccountCommand;
import com.keap.accountmanager.api.dto.trasnformer.AccountTransformer;
import com.keap.accountmanager.data.modal.Account;
import com.keap.accountmanager.data.repository.AccountRepository;
import com.keap.accountmanager.service.AccountService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(CreateAccountCommand createAccountCommand) {
        return accountRepository.save(AccountTransformer.toAccount(createAccountCommand));
    }

    @Cacheable(value = "accounts", key = "#id")
    @Override
    public Optional<Account> getAccount(String id) {
        return accountRepository.findById(id);
    }

    @Cacheable(value = "accounts")
    @Override
    public List<Account> getAccountByName(String name) {
        return accountRepository.findAccountsByUsername(name);
    }

    @Cacheable(value = "accounts")
    @Override
    public List<Account> getAccountByCompany(String company) {
        return accountRepository.findAccountsByCompany(company);
    }

    @Cacheable(value = "accounts")
    @Override
    public List<Account> getAccountByUsernameAndAndCompany(String username, String company) {
        return accountRepository.findAccountsByUsernameAndAndCompany(username, company);
    }

    @CachePut(value = "accounts", key = "#account.id")
    @Override
    public Account editAccount(Account account, EditAccountCommand editAccountCommand) {
        account.setCompany(editAccountCommand.getCompany());
        account.setTitle(editAccountCommand.getTitle());
        return accountRepository.save(account);
    }

    @CacheEvict(value="accounts", key="#id")
    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }
}
