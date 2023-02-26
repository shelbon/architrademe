package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Skill;
import com.esgi.groupe5.architrademe.kernel.Command;

public final class SaveSkillCommand implements Command {
    public final Skill skill;
    public final Profil profil;

    public SaveSkillCommand(Skill skill, Profil profil) {
        this.skill = skill;
        this.profil = profil;
    }

}
