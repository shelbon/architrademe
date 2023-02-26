package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByNameModalityQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.ModalityPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Modality;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByNameModalityService implements QueryHandler<FindByNameModalityQuery, Modality> {
    final ModalityPort modalityPort;

    public FindByNameModalityService(ModalityPort modalityPort) {
        this.modalityPort = modalityPort;
    }

    @Override
    public Modality handle(FindByNameModalityQuery query) {

        return modalityPort.findByName(query.name);
    }

}
