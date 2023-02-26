package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class RoleId {
    private final UUID value;

    public RoleId(UUID value) {
        this.value = value;
    }

    public static RoleId of(UUID value) {
        return new RoleId(value);
    }
    
    public String value() {
        return value.toString();
    }
}
