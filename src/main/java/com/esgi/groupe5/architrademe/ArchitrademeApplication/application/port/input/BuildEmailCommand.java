package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Command;

public final class BuildEmailCommand implements Command {
    public final String name;
    public  final String link;

    public BuildEmailCommand(String name, String link) {
        this.name = name;
        this.link = link;
    }

}
