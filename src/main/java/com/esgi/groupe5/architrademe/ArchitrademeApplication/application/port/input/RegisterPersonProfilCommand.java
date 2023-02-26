package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.kernel.Command;

public final class RegisterPersonProfilCommand implements Command{
    public final Profil profil;

    public RegisterPersonProfilCommand(Profil profil) {
        this.profil = profil;
    }
    
}
