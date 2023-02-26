package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.Date;

import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offer {
    private final OfferId offerId;

    private String name;

    @Lob
    private String description;

    @JsonIgnore
    private Person person;

    private Date enDate;

    private ENUMTypeContract typeContract;

    private Date createdAt;

    private Date updatedAt;

    private Boolean enable;

    public Offer(OfferId offerId, String name, String description, Person person,
            Date enDate, ENUMTypeContract typeContract) {
        this.offerId = offerId;
        this.name = name;
        this.description = description;
        this.person = person;
        this.enDate = enDate;
        this.typeContract = typeContract;
        Date d = new Date();
        this.enable = enDate.after(d);
    }

    public Offer(OfferId offerId, String name, String description, Person person, Date enDate,
                 ENUMTypeContract typeContract, Date createdAt, Date updatedAt, Boolean enable) {
        this.offerId = offerId;
        this.name = name;
        this.description = description;
        this.person = person;
        this.enDate = enDate;
        this.typeContract = typeContract;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.enable = enable;
    }

}
