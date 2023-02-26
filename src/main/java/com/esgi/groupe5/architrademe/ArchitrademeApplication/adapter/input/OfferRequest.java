package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMTypeContract;

public final class OfferRequest {
    @NotBlank
    public  String name;
    @NotBlank
    public  String description;
   
    public  Date enDate;
    
    @NotBlank
    public  String user;

    public ENUMTypeContract typeContract;

}
