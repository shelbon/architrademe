package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.Optional;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, String> {
    Optional<RoleEntity> findByName(ENUMRole name);

}
