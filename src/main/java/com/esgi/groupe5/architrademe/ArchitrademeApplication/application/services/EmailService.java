package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.BuildEmailCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.EmailSenderPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public class EmailService implements CommandHandler<BuildEmailCommand, Void> {

    final EmailSenderPort emailSenderPort;

    public EmailService(EmailSenderPort emailSenderPort) {
        this.emailSenderPort = emailSenderPort;
    }

    @Override
    public Void handle(BuildEmailCommand command) {
        emailSenderPort.sendEmail(command.name, command.link);
        return null;
    }
}