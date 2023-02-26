package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindAllOfferQuery implements Query {
    public final Boolean enable;

    public FindAllOfferQuery(Boolean enable) {
        this.enable = enable;
    }

}
