package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonProfilPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Modality;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ModalityId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.PersonId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ProfilId;

public class ProfilPersistenceAdapter implements PersonProfilPort {

    private final ProfilRepository profilRepository;

    public ProfilPersistenceAdapter(ProfilRepository profilRepository) {
        this.profilRepository = profilRepository;
    }

    @Override
    public Profil save(Profil profil) {
        var u = new PersonEntity(profil.getPerson().getPersonId().value(),
                profil.getPerson().getUsername(), profil.getPerson().getLastName(),
                profil.getPerson().getFirstName(), profil.getPerson().getEmail(),
                profil.getPerson().getPhone(), profil.getPerson().getPassword());

        Set<ModalityEntity> mod = new HashSet<ModalityEntity>();

        for (Modality mEntity : profil.getModalities()) {
            mod.add(new ModalityEntity(mEntity.getModalityId().value(),
                    mEntity.getName(), mEntity.getCreatedAt(), mEntity.getUpdatedAt()));
        }

        var p = new ProfilEntity(profil.getProfilId().value(),
                profil.getDisponibility(), profil.getTjm(), u);
        if (profil.getModalities() != null)
            p.setModalities(mod);
        profilRepository.save(p);

        return profil;
    }

    @Override
    public Profil findById(String id) {

        final Optional<ProfilEntity> optionalEntity = profilRepository.findById(id);
        if (optionalEntity.isPresent()) {
            var pEntity = optionalEntity.get();

            var u = new Person(PersonId.of(UUID.fromString(pEntity.getPerson().getId())),
                    pEntity.getPerson().getUsername(),
                    pEntity.getPerson().getLastName(),
                    pEntity.getPerson().getFirstName(), pEntity.getPerson().getEmail(),
                    pEntity.getPerson().getPhone(), pEntity.getPerson().getPassword());

            Set<Modality> mod = new HashSet<Modality>();
            for (ModalityEntity mEntity : pEntity.getModalities()) {
                mod.add(new Modality(ModalityId.of(UUID.fromString(mEntity.getId())),
                        mEntity.getName(), mEntity.getCreatedAt(), mEntity.getUpdatedAt()));
            }

            return new Profil(ProfilId.of(UUID.fromString(pEntity.getId())),
                    pEntity.getDisponibility(), pEntity.getTjm(), u, pEntity.getCreatedAt(),
                    pEntity.getUpdatedAt(), mod);

        }
        return null;
    }

    @Override
    public Profil findByPerson(Person person) {
        var perEn = new PersonEntity(person.getPersonId().value(),
                person.getUsername(), person.getLastName(), person.getFirstName(),
                person.getEmail(), person.getPhone(), person.getPassword());
        final Optional<ProfilEntity> optionalEntity = profilRepository.findByPerson(perEn);

        if (optionalEntity.isPresent()) {
            var pEntity = optionalEntity.get();

            var u = new Person(PersonId.of(UUID.fromString(pEntity.getPerson().getId())),
                    pEntity.getPerson().getUsername(),
                    pEntity.getPerson().getLastName(),
                    pEntity.getPerson().getFirstName(), pEntity.getPerson().getEmail(),
                    pEntity.getPerson().getPhone(), pEntity.getPerson().getPassword());

            Set<Modality> mod = new HashSet<Modality>();
            for (ModalityEntity mEntity : pEntity.getModalities()) {
                mod.add(new Modality(ModalityId.of(UUID.fromString(mEntity.getId())),
                        mEntity.getName(), mEntity.getCreatedAt(), mEntity.getUpdatedAt()));
            }

            return new Profil(ProfilId.of(UUID.fromString(pEntity.getId())),
                    pEntity.getDisponibility(), pEntity.getTjm(), u, pEntity.getCreatedAt(), pEntity.getUpdatedAt(),
                    mod);

        }
        return null;
    }
}
