package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByPersonProfilSkillQuery implements Query {
    public final Profil profil;

    public FindByPersonProfilSkillQuery(Profil profil) {
        this.profil = profil;
    }

}
