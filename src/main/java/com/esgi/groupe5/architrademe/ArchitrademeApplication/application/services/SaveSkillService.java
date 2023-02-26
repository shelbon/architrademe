package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SaveSkillCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.SkillPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public final class SaveSkillService implements CommandHandler<SaveSkillCommand, Void> {
    final SkillPort skillPort;

    public SaveSkillService(SkillPort skillPort) {
        this.skillPort = skillPort;
    }

    @Override
    public Void handle(SaveSkillCommand command) {
        skillPort.save(command.skill, command.profil);
        return null;
    }

}
