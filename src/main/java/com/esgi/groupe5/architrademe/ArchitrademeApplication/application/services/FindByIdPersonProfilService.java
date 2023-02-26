package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdPersonProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonProfilPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByIdPersonProfilService implements QueryHandler<FindByIdPersonProfilQuery, Profil> {
    final PersonProfilPort personProfilPort;

    public FindByIdPersonProfilService(PersonProfilPort personProfilPort) {
        this.personProfilPort = personProfilPort;
    }

    @Override
    public Profil handle(FindByIdPersonProfilQuery query) {
        return personProfilPort.findByPerson(query.person);
    }

    
}
