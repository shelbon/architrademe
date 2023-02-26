package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.exception;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.RoleId;

public class RoleException extends RuntimeException {
    private RoleException(String message) {
        super(message);
    }

    public static RoleException notFoundRoleId(RoleId roleId) {
        return new RoleException(String.format("%s not found.", roleId.getValue()));
    }

    public static RoleException notFoundName(String name) {
        return new RoleException(String.format("%s not found.", name));
    }
}
