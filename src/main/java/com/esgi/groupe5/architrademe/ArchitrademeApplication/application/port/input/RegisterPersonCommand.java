package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.Command;

public final class RegisterPersonCommand implements Command {
    public final Person person;

    public RegisterPersonCommand(Person person) {
        this.person = person;
    }

}
