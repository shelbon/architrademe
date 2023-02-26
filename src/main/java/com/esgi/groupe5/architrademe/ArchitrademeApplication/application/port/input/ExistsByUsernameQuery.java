package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class ExistsByUsernameQuery implements Query {
    public final String username;

    public ExistsByUsernameQuery(String username) {
        this.username = username;
    }

}
