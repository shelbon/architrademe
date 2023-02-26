package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "roles")
@Data
public class RoleEntity {

    @Id
    private  String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ENUMRole name;

    public RoleEntity(String id, ENUMRole name) {
        this.id = id;
        this.name = name;
    }

}
