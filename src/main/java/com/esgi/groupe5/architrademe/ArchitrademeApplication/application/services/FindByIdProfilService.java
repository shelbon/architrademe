package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonProfilPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByIdProfilService implements QueryHandler<FindByIdProfilQuery, Profil>{
    final PersonProfilPort personProfilPort;

    public FindByIdProfilService(PersonProfilPort personProfilPort) {
        this.personProfilPort = personProfilPort;
    }

    @Override
    public Profil handle(FindByIdProfilQuery query) {
        return personProfilPort.findById(query.id);
    }

}
