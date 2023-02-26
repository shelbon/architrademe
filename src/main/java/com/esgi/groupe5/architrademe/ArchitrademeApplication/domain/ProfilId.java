package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ProfilId {
    private final UUID value;

    public ProfilId(UUID value) {
        this.value = value;
    }

    public static ProfilId of(UUID value) {
        return new ProfilId(value);
    }

    public String value() {
        return value.toString();
    }
}
