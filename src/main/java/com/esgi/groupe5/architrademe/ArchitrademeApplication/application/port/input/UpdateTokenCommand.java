package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationToken;
import com.esgi.groupe5.architrademe.kernel.Command;

public final class UpdateTokenCommand implements Command {

    public final ConfirmationToken confirmationToken;

    public final String token;

    public UpdateTokenCommand(ConfirmationToken confirmationToken, String token) {
        this.confirmationToken = confirmationToken;
        this.token = token;
    }

}
