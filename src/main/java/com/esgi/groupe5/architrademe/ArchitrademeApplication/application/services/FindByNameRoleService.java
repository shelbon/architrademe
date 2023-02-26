package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByNameRoleQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.RolePort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Role;
import com.esgi.groupe5.architrademe.kernel.QueryHandler;

public final class FindByNameRoleService implements QueryHandler<FindByNameRoleQuery, Role> {
    final RolePort rolePort;

    public FindByNameRoleService(RolePort rolePort) {
        this.rolePort = rolePort;
    }

    @Override
    public Role handle(FindByNameRoleQuery query) {
        return rolePort.findByName(query.name);
    }

}
