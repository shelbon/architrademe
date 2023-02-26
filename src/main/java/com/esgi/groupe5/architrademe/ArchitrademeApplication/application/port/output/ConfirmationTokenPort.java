package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationToken;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationTokenId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;

public interface ConfirmationTokenPort {

    ConfirmationTokenId nextId();

    void saveConfirmationToken(Person person, String token);

    ConfirmationToken getToken(String token);

    ConfirmationToken findByPerson(Person person);

    void setConfirmedAt(ConfirmationToken confirmationToken);

    void updateToken(ConfirmationToken confirmationToken, String token);
}