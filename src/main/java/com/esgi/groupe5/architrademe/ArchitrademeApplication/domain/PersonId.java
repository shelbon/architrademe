package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PersonId {
    private final UUID value;

    public PersonId(UUID value) {
        this.value = value;
    }

    public static PersonId of(UUID value) {
        return new PersonId(value);
    }

    public String value() {
        return value.toString();
    }
}
