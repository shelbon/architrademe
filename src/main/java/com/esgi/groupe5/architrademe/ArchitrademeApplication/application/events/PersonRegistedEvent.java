package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.events;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.Event;

public class PersonRegistedEvent implements Event {

    private final Person person;

    public PersonRegistedEvent(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

}
