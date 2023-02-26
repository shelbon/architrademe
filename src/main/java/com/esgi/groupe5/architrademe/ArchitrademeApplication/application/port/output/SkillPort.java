package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output;

import java.util.List;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Skill;

public interface SkillPort {

    void save(Skill skill, Profil profil);

    Skill findById(String id);

    List<Skill> findByPersonProfil(Profil profil);

    Skill findByIdAndPersonProfil(String id, Profil profil);

    Skill findByName(String name);

    void delete(Skill skill);

}
