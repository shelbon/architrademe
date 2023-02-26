package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMRole;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Role;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.RoleId;

public interface RolePort {

    RoleId nextId();

    Role findByName(ENUMRole name);

    void CreateRole();
}
