package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class GetTokenQuery implements Query {
   public final String token;

    public GetTokenQuery(String token) {
        this.token = token;
    }

}
