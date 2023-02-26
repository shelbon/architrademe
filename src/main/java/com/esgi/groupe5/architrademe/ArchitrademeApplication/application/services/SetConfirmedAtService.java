package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SetConfirmedAtCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.ConfirmationTokenPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public final class SetConfirmedAtService implements CommandHandler<SetConfirmedAtCommand, Void> {
    final ConfirmationTokenPort confirmationTokenPort;

    public SetConfirmedAtService(ConfirmationTokenPort confirmationTokenPort) {
        this.confirmationTokenPort = confirmationTokenPort;
    }

    @Override
    public Void handle(SetConfirmedAtCommand command) {
        confirmationTokenPort.setConfirmedAt(command.confirmToken);
        return null;
    }

}
