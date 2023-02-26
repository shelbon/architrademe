package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.Optional;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMModality;
import org.springframework.data.repository.CrudRepository;

public interface ModalityRepository extends CrudRepository<ModalityEntity, String> {
    Optional<ModalityEntity> findByName(ENUMModality name);

}
