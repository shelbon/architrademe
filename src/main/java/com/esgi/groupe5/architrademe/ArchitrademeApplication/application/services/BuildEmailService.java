package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.BuildEmailQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonPort;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class BuildEmailService implements QueryHandler<BuildEmailQuery, String> {
    final PersonPort personPort;

    public BuildEmailService(PersonPort personPort) {
        this.personPort = personPort;
    }

    @Override
    public String handle(BuildEmailQuery query) {
     return personPort.buildEmail(query.name, query.link);
    }

}
