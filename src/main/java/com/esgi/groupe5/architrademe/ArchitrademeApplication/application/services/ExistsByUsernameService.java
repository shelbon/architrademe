package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.ExistsByUsernameQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonPort;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class ExistsByUsernameService implements QueryHandler<ExistsByUsernameQuery, Boolean> {
    final PersonPort personPort;

    public ExistsByUsernameService(PersonPort personPort) {
        this.personPort = personPort;
    }

    @Override
    public Boolean handle(ExistsByUsernameQuery query) {
        return personPort.existsByUsername(query.username);
    }

}
