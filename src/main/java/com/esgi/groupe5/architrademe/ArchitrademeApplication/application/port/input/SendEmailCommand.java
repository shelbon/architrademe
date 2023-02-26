package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Command;

public final class SendEmailCommand implements Command {
    public final String to;
    public  final String email;

    public SendEmailCommand(String to, String email) {
        this.to = to;
        this.email = email;
    }

}
