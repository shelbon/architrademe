package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdOfferQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.OfferPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Offer;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByIdOfferService implements QueryHandler<FindByIdOfferQuery, Offer> {
    final OfferPort offerPort;

    public FindByIdOfferService(OfferPort offerPort) {
        this.offerPort = offerPort;
    }

    @Override
    public Offer handle(FindByIdOfferQuery query) {
        return offerPort.findById(query.id);
    }

}
