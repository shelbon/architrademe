package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdSkillQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.SkillPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Skill;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByIdSkillService implements QueryHandler<FindByIdSkillQuery, Skill> {
    final SkillPort skillPort;

    public FindByIdSkillService(SkillPort skillPort) {
        this.skillPort = skillPort;
    }

    @Override
    public Skill handle(FindByIdSkillQuery query) {
        return skillPort.findById(query.id);
    }

}
