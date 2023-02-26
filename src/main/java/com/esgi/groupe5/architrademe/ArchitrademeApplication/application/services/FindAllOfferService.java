package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import java.util.List;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindAllOfferQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.OfferPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Offer;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindAllOfferService implements QueryHandler<FindAllOfferQuery, List<Offer>> {
    final OfferPort offerPort;

    public FindAllOfferService(OfferPort offerPort) {
        this.offerPort = offerPort;
    }

    @Override
    public List<Offer> handle(FindAllOfferQuery query) {
        return offerPort.findByEnable(query.enable);
    }

}
