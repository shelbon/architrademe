package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.DeleteSkillCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.SkillPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public final class DeleteSkillService implements CommandHandler<DeleteSkillCommand, Void> {
    final SkillPort skillPort;

    public DeleteSkillService(SkillPort skillPort) {
        this.skillPort = skillPort;
    }

    @Override
    public Void handle(DeleteSkillCommand command) {
        skillPort.delete(command.skill);
        return null;
    }

}
