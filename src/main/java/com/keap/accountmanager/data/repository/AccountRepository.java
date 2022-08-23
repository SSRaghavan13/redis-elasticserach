package com.keap.accountmanager.data.repository;

import com.keap.accountmanager.data.modal.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AccountRepository extends ElasticsearchRepository<Account, String> {
    List<Account> findAccountsByUsername(String username);

    List<Account> findAccountsByCompany(String company);

    List<Account> findAccountsByUsernameAndAndCompany(String username, String company);
}
