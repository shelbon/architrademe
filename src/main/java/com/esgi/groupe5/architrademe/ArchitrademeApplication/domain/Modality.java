package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Modality {
    private final ModalityId modalityId;

    private ENUMModality name;

    private Date createdAt;

    private Date updatedAt;

    public Modality(ModalityId modalityId, ENUMModality name) {
        this.modalityId = modalityId;
        this.name = name;
    }

    public Modality(ModalityId modalityId, ENUMModality name, Date createdAt, Date updatedAt) {
        this.modalityId = modalityId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
