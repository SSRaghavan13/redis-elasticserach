package com.keap.accountmanager.api.command;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class CreateAccountCommand {
    @NotBlank private String username;
    @NotBlank private String company;
    @NotBlank private String title;
}
