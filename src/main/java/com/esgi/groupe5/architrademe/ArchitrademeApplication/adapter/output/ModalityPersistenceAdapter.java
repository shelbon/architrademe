package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.ModalityPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMModality;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Modality;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ModalityId;

public class ModalityPersistenceAdapter implements ModalityPort {

    private final ModalityRepository modalityRepository;

    public ModalityPersistenceAdapter(ModalityRepository modalityRepository) {
        this.modalityRepository = modalityRepository;
    }

    @Override
    public Modality findByName(ENUMModality name) {
        final Optional<ModalityEntity> optionalEntity = modalityRepository.findByName(name);

        if (optionalEntity.isPresent()) {
            var modl = optionalEntity.get();

            return new Modality(ModalityId.of(UUID.fromString(modl.getId())),
                    modl.getName(), modl.getCreatedAt(),
                    modl.getUpdatedAt());

        }
       return null;
    }

    @Override
    public void CreateModality() {
        List<ModalityEntity> role = (List<ModalityEntity>) modalityRepository.findAll();

        if (role.size() == 0) {
            ModalityEntity r1 = new ModalityEntity(nextId().value(), ENUMModality.CARTE_BANCAIRE);
            ModalityEntity r5 = new ModalityEntity(nextId().value(), ENUMModality.VIREMENT);
            modalityRepository.save(r1);
            modalityRepository.save(r5);
        }
    }

    @Override
    public ModalityId nextId() {
        return ModalityId.of(UUID.randomUUID());
    }

}
