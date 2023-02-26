package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RegisterPersonProfilCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonProfilPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public final class RegisterPersonProfilService implements CommandHandler<RegisterPersonProfilCommand, Profil> {
    final PersonProfilPort personProfilPort;

    public RegisterPersonProfilService(PersonProfilPort personProfilPort) {
        this.personProfilPort = personProfilPort;
    }

    @Override
    public Profil handle(RegisterPersonProfilCommand command) {
        return personProfilPort.save(command.profil);
    }

}
