package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMTypeContract;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offers")
@Data
public class OfferEntity {

    @Id
    private String id;

    @NotBlank
    @Size(min = 3)
    private String name;

    @NotBlank
    @Lob
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PersonEntity person;

    private Date enDate;

    private ENUMTypeContract typeContract;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    private Boolean enable;

    public OfferEntity(String id, String name, String description, PersonEntity person,
            Date enDate, ENUMTypeContract typeContract) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.person = person;
        this.enDate = enDate;
        this.typeContract = typeContract;
        Date d = new Date();
        this.enable = enDate.after(d);
    }

}
