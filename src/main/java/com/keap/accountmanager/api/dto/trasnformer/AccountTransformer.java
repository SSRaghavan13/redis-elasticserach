package com.keap.accountmanager.api.dto.trasnformer;

import com.keap.accountmanager.api.command.CreateAccountCommand;
import com.keap.accountmanager.api.dto.AccountDto;
import com.keap.accountmanager.data.modal.Account;

public class AccountTransformer {
    public static Account toAccount(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .username(accountDto.getUsername())
                .company(accountDto.getCompany())
                .title(accountDto.getTitle())
                .build();
    }

    public static AccountDto toAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .company(account.getCompany())
                .title(account.getTitle())
                .build();
    }

    public static Account toAccount(CreateAccountCommand createAccountCommand) {
        return Account.builder()
                .username(createAccountCommand.getUsername())
                .company(createAccountCommand.getCompany())
                .title(createAccountCommand.getTitle())
                .build();
    }

    private AccountTransformer() {}
}
