package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.kernel.Query;

public final class FindByIdSkillAndPersonProfilQuery implements Query {
    public final String id;
    public final Profil profil;

    public FindByIdSkillAndPersonProfilQuery(String id, Profil profil) {
        this.id = id;
        this.profil = profil;
    }

}
