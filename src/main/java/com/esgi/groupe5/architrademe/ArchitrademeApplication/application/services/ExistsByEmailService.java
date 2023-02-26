package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.ExistsByEmailQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonPort;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class ExistsByEmailService implements QueryHandler<ExistsByEmailQuery, Boolean> {
    final PersonPort personPort;

    public ExistsByEmailService(PersonPort personPort) {
        this.personPort = personPort;
    }

    @Override
    public Boolean handle(ExistsByEmailQuery query) {
        return personPort.existsByEmail(query.email);
    }

}
