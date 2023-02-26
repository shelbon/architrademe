package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProfilRepository extends CrudRepository<ProfilEntity, String> {
    Optional<ProfilEntity> findByPerson(PersonEntity person);
}
