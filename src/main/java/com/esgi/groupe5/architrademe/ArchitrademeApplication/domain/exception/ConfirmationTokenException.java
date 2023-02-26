package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.exception;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationTokenId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;

public class ConfirmationTokenException extends RuntimeException {
    private ConfirmationTokenException(String message) {
        super(message);
    }

    public static ConfirmationTokenException notFoundConfirmationTokenId(ConfirmationTokenId confirmationTokenId) {
        return new ConfirmationTokenException(String.format("%s not found.", confirmationTokenId.getValue()));
    }

    public static ConfirmationTokenException notFoundToken(String name) {
        return new ConfirmationTokenException(String.format("%s not found.", name));
    }

    public static ConfirmationTokenException notFoundPerson(Person person) {
        return new ConfirmationTokenException(String.format("%s not found.", person));
    }
}
