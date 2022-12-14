package com.keap.accountmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AccountmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountmanagerApplication.class, args);
	}

}
