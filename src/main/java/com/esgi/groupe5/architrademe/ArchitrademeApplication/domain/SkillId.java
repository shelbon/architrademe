package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class SkillId {
    private final UUID value;

    public SkillId(UUID value) {
        this.value = value;
    }

    public static SkillId of(UUID value) {
        return new SkillId(value);
    }

    public String value() {
        return value.toString();
    }
}
