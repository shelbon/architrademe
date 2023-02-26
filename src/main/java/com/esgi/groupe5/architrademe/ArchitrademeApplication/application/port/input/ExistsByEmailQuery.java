package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class ExistsByEmailQuery implements Query {
    public final String email;

    public ExistsByEmailQuery(String email) {
        this.email = email;
    }

}
