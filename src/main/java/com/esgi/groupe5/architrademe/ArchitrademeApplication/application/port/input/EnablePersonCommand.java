package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.Command;

public class EnablePersonCommand implements Command {
    public final Person person;

    public EnablePersonCommand(Person person) {
        this.person = person;
    }
    
}
