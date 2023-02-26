package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SaveConfirmationTokenCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.ConfirmationTokenPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public final class SaveConfirmationTokenService implements CommandHandler<SaveConfirmationTokenCommand, Void>{
    final ConfirmationTokenPort confirmationTokenPort;

    public SaveConfirmationTokenService(ConfirmationTokenPort confirmationTokenPort) {
        this.confirmationTokenPort = confirmationTokenPort;
    }

    @Override
    public Void handle(SaveConfirmationTokenCommand command) {
        confirmationTokenPort.saveConfirmationToken(command.person, command.token);
        return null;
    }

}
