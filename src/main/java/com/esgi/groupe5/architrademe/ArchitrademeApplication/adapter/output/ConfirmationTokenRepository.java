package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.Optional;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.ConfirmationTokenEntity;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationTokenEntity, String> {

    Optional<ConfirmationTokenEntity> findByToken(String token);

    Optional<ConfirmationTokenEntity> findByPerson(PersonEntity person);
}
