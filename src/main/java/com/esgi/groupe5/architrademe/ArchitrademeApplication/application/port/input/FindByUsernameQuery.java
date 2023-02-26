package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByUsernameQuery implements Query {
    public final String username;

    public FindByUsernameQuery(String username) {
        this.username = username;
    }

}
