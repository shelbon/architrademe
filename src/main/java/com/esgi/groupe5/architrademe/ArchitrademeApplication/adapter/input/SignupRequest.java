package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public final class SignupRequest {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 20)
    public String username;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Email(message = "Email incorrect")
    public String email;

    @NotNull
    public Set<String> role;

    @NotBlank
    @NotNull
    @Size(min = 6, max = 40)
    public String password;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 20)
    public String lastName;

    public String firstName;

    @NotNull
    public Integer phone;
}
