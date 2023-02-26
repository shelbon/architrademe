package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByNameSkillQuery implements Query {
    public final String name;

    public FindByNameSkillQuery(String name) {
        this.name = name;
    }

}
