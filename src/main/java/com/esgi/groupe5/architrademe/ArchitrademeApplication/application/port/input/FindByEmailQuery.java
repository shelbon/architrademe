package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByEmailQuery implements Query {
    public final String email;

    public FindByEmailQuery(String email) {
        this.email = email;
    }

}
