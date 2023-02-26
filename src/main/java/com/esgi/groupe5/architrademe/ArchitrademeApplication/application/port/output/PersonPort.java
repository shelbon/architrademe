package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;

public interface PersonPort {

    void register(Person person);

    Person findByUsername(String username);

    Person findByEmail(String email);

    Person findById(String id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    String buildEmail(String name, String link);

    void enablePerson(Person person);

}
