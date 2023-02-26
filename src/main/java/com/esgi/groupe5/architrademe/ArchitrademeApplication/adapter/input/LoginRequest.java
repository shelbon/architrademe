package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;


@Getter
public class LoginRequest {
    @NotEmpty(message = "user name is not empty")
    @Size(min = 2, message = "user name should have at least 2 characters")
    public String username;

    @NotEmpty
    public String password;
}
