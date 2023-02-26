package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.exception;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.PersonId;

public class PersonException extends RuntimeException {
    private PersonException(String message) {
        super(message);
    }

    public static PersonException notFoundPersonId(PersonId personId) {
        return new PersonException(String.format("%s not found.", personId.getValue()));
    }

    public static PersonException notFoundPersonUsername(String username) {
        return new PersonException(String.format("%s not found.", username));
    }
}
