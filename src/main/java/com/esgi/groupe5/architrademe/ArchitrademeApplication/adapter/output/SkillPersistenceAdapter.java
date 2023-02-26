package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.SkillPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Skill;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.SkillId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.exception.SkillException;

public class SkillPersistenceAdapter implements SkillPort {

        public final SkillRepository skillRepository;

        public SkillPersistenceAdapter(SkillRepository skillRepository) {
                this.skillRepository = skillRepository;
        }

        @Override
        public void save(Skill skill, Profil profil) {

                var u = new PersonEntity(profil.getPerson().getPersonId().value(),
                                profil.getPerson().getUsername(), profil.getPerson().getLastName(),
                                profil.getPerson().getFirstName(), profil.getPerson().getEmail(),
                                profil.getPerson().getPhone(), profil.getPerson().getPassword());

                var p = new ProfilEntity(profil.getProfilId().value(),
                                profil.getDisponibility(), profil.getTjm(), u);

                var sk = new SkillEntity(skill.getSkillId().value(),
                                skill.getName(), skill.getNotion(), p);

                skillRepository.save(sk);

        }

        @Override
        public Skill findById(String id) {

                final Optional<SkillEntity> optionalEntity = skillRepository.findById(id);
                if (optionalEntity.isPresent()) {
                        var skill = optionalEntity.get();

                        return new Skill(SkillId.of(UUID.fromString(skill.getId())),
                                        skill.getName(), skill.getNotion(), skill.getCreatedAt(),
                                        skill.getUpdatedAt());
                }
                return null;

        }

        @Override
        public Skill findByName(String name) {

                final Optional<SkillEntity> optionalEntity = skillRepository.findByName(name);
                if (optionalEntity.isPresent()) {
                        var skill = optionalEntity.get();

                        return new Skill(SkillId.of(UUID.fromString(skill.getId())),
                                        skill.getName(), skill.getNotion(), skill.getCreatedAt(),
                                        skill.getUpdatedAt());
                }
                return null;
        }

        @Override
        public void delete(Skill skill) {
                Optional<SkillEntity> optionalEntity = skillRepository.findById(skill.getSkillId().value());

                if (optionalEntity.isPresent()) {
                        skillRepository.delete(optionalEntity.get());
                } else
                        throw SkillException.notFoundSkillId(skill.getSkillId());
        }

        @Override
        public List<Skill> findByPersonProfil(Profil profil) {
                var ut = new PersonEntity(profil.getPerson().getPersonId().value(),
                                profil.getPerson().getUsername(),
                                profil.getPerson().getLastName(),
                                profil.getPerson().getFirstName(), profil.getPerson().getEmail(),
                                profil.getPerson().getPhone(), profil.getPerson().getPassword());

                var pe = new ProfilEntity(profil.getProfilId().value(),
                                profil.getDisponibility(), profil.getTjm(), ut);

                var list = skillRepository.findByPersonProfil(pe);
                List<Skill> lSkills = new ArrayList<Skill>();
                for (SkillEntity skill : list) {

                        lSkills.add(new Skill(SkillId.of(UUID.fromString(skill.getId())),
                                        skill.getName(), skill.getNotion(), skill.getCreatedAt(),
                                        skill.getUpdatedAt()));
                }
                return lSkills;
        }

        @Override
        public Skill findByIdAndPersonProfil(String id, Profil profil) {
                var ut = new PersonEntity(profil.getPerson().getPersonId().value(),
                                profil.getPerson().getUsername(),
                                profil.getPerson().getLastName(),
                                profil.getPerson().getFirstName(), profil.getPerson().getEmail(),
                                profil.getPerson().getPhone(), profil.getPerson().getPassword());

                var pe = new ProfilEntity(profil.getProfilId().value(),
                                profil.getDisponibility(), profil.getTjm(), ut);

                Optional<SkillEntity> skill = skillRepository.findByIdAndPersonProfil(id, pe);
                if (skill.isPresent())
                        return new Skill(SkillId.of(UUID.fromString(skill.get().getId())),
                                        skill.get().getName(), skill.get().getNotion(), skill.get().getCreatedAt(),
                                        skill.get().getUpdatedAt());
                else
                        return null;
        }
}
