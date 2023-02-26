package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.exception;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.SkillId;

public class SkillException extends RuntimeException {
    private SkillException(String message) {
        super(message);
    }

    public static SkillException notFoundSkillId(SkillId skillId) {
        return new SkillException(String.format("%s not found.", skillId.getValue()));
    }

    public static SkillException notFoundSkillName(String name) {
        return new SkillException(String.format("%s not found.", name));
    }
}
