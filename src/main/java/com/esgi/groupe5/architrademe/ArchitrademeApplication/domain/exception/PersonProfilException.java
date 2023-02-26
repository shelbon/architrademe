package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.exception;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ProfilId;

public class PersonProfilException extends RuntimeException {
    private PersonProfilException(String message) {
        super(message);
    }

    public static PersonProfilException notFoundPersonProfilId(ProfilId profilId) {
        return new PersonProfilException(String.format("%s not found.", profilId.getValue()));
    }

    public static PersonProfilException notFoundPerson(Person person) {
        return new PersonProfilException(String.format("%s not found.", person));
    }
}
