package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.UpdateTokenCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.ConfirmationTokenPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public final class UpdateTokenService implements CommandHandler<UpdateTokenCommand, Void> {
    final ConfirmationTokenPort confirmationTokenPort;

    public UpdateTokenService(ConfirmationTokenPort confirmationTokenPort) {
        this.confirmationTokenPort = confirmationTokenPort;
    }

    @Override
    public Void handle(UpdateTokenCommand command) {
        confirmationTokenPort.updateToken(command.confirmationToken, command.token);
        return null;
    }

}
