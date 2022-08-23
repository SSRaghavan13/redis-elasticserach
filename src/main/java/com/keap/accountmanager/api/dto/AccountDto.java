package com.keap.accountmanager.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class AccountDto implements Serializable {

    private static final long serialVersionUID = 4439114469417994311L;

    private String id;
    private String username;
    private String company;
    private String title;

    @Override
    public String toString() {
        return "AccountDto{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", company='" + company + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
