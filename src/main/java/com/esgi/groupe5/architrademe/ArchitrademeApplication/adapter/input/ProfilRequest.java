package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import java.util.Set;

import javax.validation.constraints.NotNull;

public final class ProfilRequest {
    public Float tjm;
    public Boolean disponibility;
    
    public Set<String> modality;
    @NotNull
    public String user;
}
