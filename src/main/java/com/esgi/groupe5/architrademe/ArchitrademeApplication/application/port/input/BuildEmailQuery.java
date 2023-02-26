package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.kernel.Query;

public final class BuildEmailQuery implements Query {
    public final String name;
    public  final String link;

    public BuildEmailQuery(String name, String link) {
        this.name = name;
        this.link = link;
    }

}
