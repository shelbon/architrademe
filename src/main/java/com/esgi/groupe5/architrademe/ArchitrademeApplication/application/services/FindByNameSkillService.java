package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByNameSkillQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.SkillPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Skill;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByNameSkillService implements QueryHandler<FindByNameSkillQuery, Skill> {
    final SkillPort skillPort;

    public FindByNameSkillService(SkillPort skillPort) {
        this.skillPort = skillPort;
    }

    @Override
    public Skill handle(FindByNameSkillQuery query) {
        return skillPort.findByName(query.name);
    }

}
