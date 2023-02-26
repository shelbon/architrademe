package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByEmailQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByEmailService implements QueryHandler<FindByEmailQuery, Person> {
    final PersonPort personPort;

    public FindByEmailService(PersonPort personPort) {
        this.personPort = personPort;
    }

    @Override
    public Person handle(FindByEmailQuery query) {
        return personPort.findByEmail(query.email);
    }

}
