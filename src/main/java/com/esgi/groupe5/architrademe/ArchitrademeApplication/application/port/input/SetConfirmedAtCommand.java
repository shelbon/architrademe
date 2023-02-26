package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationToken;
import com.esgi.groupe5.architrademe.kernel.Command;

public final class SetConfirmedAtCommand implements Command {
    public final ConfirmationToken confirmToken;

    public SetConfirmedAtCommand(ConfirmationToken confirmToken) {
        this.confirmToken = confirmToken;
    }

}
