package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.GetTokenQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.ConfirmationTokenPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationToken;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class GetTokenService implements QueryHandler<GetTokenQuery, ConfirmationToken> {
    final ConfirmationTokenPort confirmationTokenPort;

    public GetTokenService(ConfirmationTokenPort confirmationTokenPort) {
        this.confirmationTokenPort = confirmationTokenPort;
    }

    @Override
    public ConfirmationToken handle(GetTokenQuery query) {
        return confirmationTokenPort.getToken(query.token);
    }

}
