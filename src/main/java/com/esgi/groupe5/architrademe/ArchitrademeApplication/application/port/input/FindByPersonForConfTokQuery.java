package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByPersonForConfTokQuery implements Query {
    public final Person person;

    public FindByPersonForConfTokQuery(Person person) {
        this.person = person;
    }

}
