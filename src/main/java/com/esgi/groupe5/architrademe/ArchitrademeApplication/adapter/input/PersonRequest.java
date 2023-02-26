package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public final class PersonRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    public String username;

    @NotBlank
    @Size(max = 50)
    @Email(message = "Email incorrect")
    public String email;

    @NotBlank
    @Size(min = 3, max = 20)
    public String lastName;

    public String firstName;
    @NotNull
    public Integer phone;
}
