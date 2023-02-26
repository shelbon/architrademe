package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdSkillAndPersonProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.SkillPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Skill;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByIdSkillAndPersonProfilService
        implements QueryHandler<FindByIdSkillAndPersonProfilQuery, Skill> {
    final SkillPort skillPort;

    public FindByIdSkillAndPersonProfilService(SkillPort skillPort) {
        this.skillPort = skillPort;
    }

    @Override
    public Skill handle(FindByIdSkillAndPersonProfilQuery query) {
        return skillPort.findByIdAndPersonProfil(query.id, query.profil);
    }

}
