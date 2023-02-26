package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import java.util.List;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByPersonProfilSkillQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.SkillPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Skill;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByPersonProfilSkillService implements QueryHandler<FindByPersonProfilSkillQuery,  List<Skill>> {
    final SkillPort skillPort;

    public FindByPersonProfilSkillService(SkillPort skillPort) {
        this.skillPort = skillPort;
    }

    @Override
    public  List<Skill> handle(FindByPersonProfilSkillQuery query) {
      return skillPort.findByPersonProfil(query.profil);
    }

}
