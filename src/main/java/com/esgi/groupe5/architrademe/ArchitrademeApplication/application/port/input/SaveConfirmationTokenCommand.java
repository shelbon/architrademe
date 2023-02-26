package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.Command;

public final class SaveConfirmationTokenCommand implements Command {
   public final Person person;
   public final String token;

    public SaveConfirmationTokenCommand(Person person, String token) {
        this.person = person;
        this.token = token;
    }

}
