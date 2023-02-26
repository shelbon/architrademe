package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdPersonQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByIdPersonService implements QueryHandler<FindByIdPersonQuery, Person> {
    final PersonPort personPort;

    public FindByIdPersonService(PersonPort personPort) {
        this.personPort = personPort;
    }

    @Override
    public Person handle(FindByIdPersonQuery query) {
        return personPort.findById(query.id);
    }

}
