package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMTypeContract;

public final class OfferUpdateRequest {
    @NotBlank
    public  String name;
    @NotBlank
    public  String description;
   
    public  Date enDate;
    public ENUMTypeContract typeContract;

}
