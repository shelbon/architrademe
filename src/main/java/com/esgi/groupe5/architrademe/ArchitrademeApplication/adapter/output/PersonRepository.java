package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.Optional;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.PersonEntity;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<PersonEntity, String> {

    Optional<PersonEntity> findByUsername(String username);

    Optional<PersonEntity> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
