package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ModalityId {
    private final UUID value;

    public ModalityId(UUID value) {
        this.value = value;
    }

    public static ModalityId of(UUID value) {
        return new ModalityId(value);
    }

    public String value() {
        return value.toString();
    }
}
