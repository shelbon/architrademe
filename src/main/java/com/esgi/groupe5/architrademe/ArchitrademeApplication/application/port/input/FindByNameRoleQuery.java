package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMRole;
import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByNameRoleQuery implements Query {
    public final ENUMRole name;

    public FindByNameRoleQuery(ENUMRole name) {
        this.name = name;
    }

}
