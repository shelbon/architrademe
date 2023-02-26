package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Profil {

    private ProfilId profilId;

    private Boolean disponibility = true;

    private Integer tjm;

    @JsonIgnore
    private Person person;

    private Date createdAt;

    private Date updatedAt;

    private Set<Modality> modalities = new HashSet<Modality>();

    public Profil(ProfilId profilId, Boolean disponibility, Integer tjm, Person person) {
        this.profilId = profilId;
        this.disponibility = disponibility;
        this.tjm = tjm;
        this.person = person;
    }

    public Profil(ProfilId profilId, Boolean disponibility, Integer tjm, Person person,
                  Date createdAt, Date updatedAt, Set<Modality> modalities) {
        this.profilId = profilId;
        this.disponibility = disponibility;
        this.tjm = tjm;
        this.person = person;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.modalities = modalities;
    }

    public Profil(ProfilId profilId, Boolean disponibility, Integer tjm, Date createdAt,
                  Date updatedAt, Set<Modality> modalities) {
        this.profilId = profilId;
        this.disponibility = disponibility;
        this.tjm = tjm;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.modalities = modalities;
    }
}
