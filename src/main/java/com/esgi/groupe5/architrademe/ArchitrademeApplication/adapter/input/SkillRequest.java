package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMNotion;

public final class SkillRequest {
    @NotBlank
    @Size(min = 3)
    public String name;
    public ENUMNotion notion;
    @NotBlank
    public String profil;
}
