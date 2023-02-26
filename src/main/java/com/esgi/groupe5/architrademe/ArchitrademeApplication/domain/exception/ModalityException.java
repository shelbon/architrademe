package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.exception;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMModality;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ModalityId;

public class ModalityException extends RuntimeException {
    private ModalityException(String message) {
        super(message);
    }

    public static ModalityException notFoundModalityId(ModalityId modalityId) {
        return new ModalityException(String.format("%s not found.", modalityId.getValue()));
    }

    public static ModalityException notFoundModalityName(ENUMModality name) {
        return new ModalityException(String.format("%s not found.", name));
    }
}
