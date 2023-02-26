package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMModality;
import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByNameModalityQuery implements Query {
    public  final ENUMModality name;

    public FindByNameModalityQuery(ENUMModality name) {
        this.name = name;
    }

}
