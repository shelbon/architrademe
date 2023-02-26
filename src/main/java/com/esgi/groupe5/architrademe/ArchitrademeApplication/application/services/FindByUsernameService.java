package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByUsernameQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByUsernameService implements QueryHandler<FindByUsernameQuery, Person> {
    final PersonPort personPort;

    public FindByUsernameService(PersonPort personPort) {
        this.personPort = personPort;
    }

    @Override
    public Person handle(FindByUsernameQuery query) {
        return personPort.findByUsername(query.username);
    }


}
