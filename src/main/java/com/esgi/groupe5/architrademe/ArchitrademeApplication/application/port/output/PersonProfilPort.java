package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;

public interface PersonProfilPort {
    
    Profil save(Profil profil);

    Profil findById(String id);

    Profil findByPerson(Person person);

}
