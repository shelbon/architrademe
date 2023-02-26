package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class OfferId {
    private final UUID value;

    public OfferId(UUID value) {
        this.value = value;
    }

    public static OfferId of(UUID value) {
        return new OfferId(value);
    }

    public String value() {
        return value.toString();
    }
}
