package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMModality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "modalities")
@Data
public class ModalityEntity {

    @Id
    private String id;

    private ENUMModality name;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public ModalityEntity(String id, ENUMModality name) {
        this.id = id;
        this.name = name;
    }

}
