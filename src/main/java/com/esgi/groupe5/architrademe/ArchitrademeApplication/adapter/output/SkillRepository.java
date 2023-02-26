package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<SkillEntity, String> {
    List<SkillEntity> findByPersonProfil(ProfilEntity personProfil);

    Optional<SkillEntity> findByName(String name);

    Optional<SkillEntity> findByIdAndPersonProfil(String id, ProfilEntity profilEntity);

}
