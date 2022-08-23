package com.keap.accountmanager.service;

import com.keap.accountmanager.api.command.CreateAccountCommand;
import com.keap.accountmanager.api.command.EditAccountCommand;
import com.keap.accountmanager.data.modal.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(CreateAccountCommand createAccountCommand);

    Optional<Account> getAccount(String id);

    List<Account> getAccountByName(String name);

    List<Account> getAccountByCompany(String company);

    List<Account> getAccountByUsernameAndAndCompany(String username, String company);

    Account editAccount(Account account, EditAccountCommand editAccountCommand);

    void deleteAccount(String id);
}
