package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profil_persons", uniqueConstraints = {
        @UniqueConstraint(columnNames = "person_id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfilEntity {

    @Id
    private String id;

    private Boolean disponibility = true;

    private Integer tjm;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    @JsonIgnore
    private PersonEntity person;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "profil_modalities", joinColumns = @JoinColumn(name = "profil_id"), inverseJoinColumns = @JoinColumn(name = "modality_id"))
    private Set<ModalityEntity> modalities = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "personProfil")
    private Set<SkillEntity> skills = new HashSet<>();

    public ProfilEntity(String id, Boolean disponibility, Integer tjm, PersonEntity person) {
        this.id = id;
        this.disponibility = disponibility;
        this.tjm = tjm;
        this.person = person;
    }

}
