package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Skill;
import com.esgi.groupe5.architrademe.kernel.Command;

public final class DeleteSkillCommand implements Command {
    public  final Skill skill;

    public DeleteSkillCommand(Skill skill) {
        this.skill = skill;
    }

}
