package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMNotion;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "skills")
@Data
public class SkillEntity {

    @Id
    private String id;

    @NotBlank
    private String name;

    private ENUMNotion notion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profil_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProfilEntity personProfil;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public SkillEntity(String id, String name, ENUMNotion notion, ProfilEntity personProfil) {
        this.id = id;
        this.name = name;
        this.notion = notion;
        this.personProfil = personProfil;
    }

}
