package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByIdProfilQuery implements Query {
    public final String id;

    public FindByIdProfilQuery(String id) {
        this.id = id;
    }

}
