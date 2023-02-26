package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByPersonForConfTokQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.ConfirmationTokenPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationToken;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByPersonForConfTokService
        implements QueryHandler<FindByPersonForConfTokQuery, ConfirmationToken> {
    final ConfirmationTokenPort confirmationTokenPort;

    public FindByPersonForConfTokService(ConfirmationTokenPort confirmationTokenPort) {
        this.confirmationTokenPort = confirmationTokenPort;
    }

    @Override
    public ConfirmationToken handle(FindByPersonForConfTokQuery query) {
        return confirmationTokenPort.findByPerson(query.person);
    }

}
