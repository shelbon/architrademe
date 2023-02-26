package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.RolePort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMRole;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Role;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.RoleId;

public class RolePersistenceAdapter implements RolePort {

    private final RoleRepository roleRepository;

    public RolePersistenceAdapter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(ENUMRole name) {

        final Optional<RoleEntity> optionalEntity = roleRepository.findByName(name);
        if (optionalEntity.isPresent()) {
            var p = optionalEntity.get();
            return new Role(RoleId.of(UUID.fromString(p.getId())), p.getName());
        }
        return null;
    }

    @Override
    public void CreateRole() {
        List<RoleEntity> RoleEntity = (List<RoleEntity>) roleRepository.findAll();

        if (RoleEntity.size() == 0) {
            RoleEntity r2 = new RoleEntity(nextId().value(), ENUMRole.ROLE_CLIENT);
            RoleEntity r3 = new RoleEntity(nextId().value(), ENUMRole.ROLE_CONSULTANT);
            roleRepository.save(r2);
            roleRepository.save(r3);
        }
    }

    @Override
    public RoleId nextId() {
        return RoleId.of(UUID.randomUUID());
    }

}
