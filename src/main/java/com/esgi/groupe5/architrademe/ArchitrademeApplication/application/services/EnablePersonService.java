package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.EnablePersonCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public class EnablePersonService implements CommandHandler<EnablePersonCommand, Void> {
    final PersonPort personPort;

    public EnablePersonService(PersonPort personPort) {
        this.personPort = personPort;
    }

    @Override
    public Void handle(EnablePersonCommand command) {
        personPort.enablePerson(command.person);
        return null;
    }

}
