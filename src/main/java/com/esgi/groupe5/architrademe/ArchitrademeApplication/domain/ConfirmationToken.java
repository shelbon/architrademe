package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmationToken {
    private final ConfirmationTokenId confirmationTokenId;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    private Person person;

    public ConfirmationToken(ConfirmationTokenId confirmationTokenId, String token, LocalDateTime createdAt,
            LocalDateTime expiresAt, LocalDateTime confirmedAt, Person person) {
        this.confirmationTokenId = confirmationTokenId;
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.confirmedAt = confirmedAt;
        this.person = person;
    }

}
