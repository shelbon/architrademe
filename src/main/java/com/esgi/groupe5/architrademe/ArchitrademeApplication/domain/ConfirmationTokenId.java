package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ConfirmationTokenId {
    private final UUID value;

    public ConfirmationTokenId(UUID value) {
        this.value = value;
    }

    public static ConfirmationTokenId of(UUID value) {
        return new ConfirmationTokenId(value);
    }

    public String value() {
        return value.toString();
    }
}
