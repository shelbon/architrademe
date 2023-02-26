package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Role {
    private final RoleId roleId;

    private ENUMRole name;

    public Role(RoleId roleId, ENUMRole name) {
        this.roleId = roleId;
        this.name = name;
    }

}
