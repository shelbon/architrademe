package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByIdOfferQuery implements Query {
    public final String id;

    public FindByIdOfferQuery(String id) {
        this.id = id;
    }

}
