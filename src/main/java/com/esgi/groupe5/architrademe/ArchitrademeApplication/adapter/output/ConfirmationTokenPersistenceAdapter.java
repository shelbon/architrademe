package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.ConfirmationTokenPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationToken;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationTokenId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.PersonId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Role;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.RoleId;

public class ConfirmationTokenPersistenceAdapter implements ConfirmationTokenPort {

        private final ConfirmationTokenRepository confirmationTokenRepository;

        public ConfirmationTokenPersistenceAdapter(ConfirmationTokenRepository confirmationTokenRepository) {
                this.confirmationTokenRepository = confirmationTokenRepository;
        }

        @Override
        public ConfirmationToken getToken(String token) {

                final Optional<ConfirmationTokenEntity> optionalEntity = confirmationTokenRepository.findByToken(token);
                if (optionalEntity.isPresent()) {
                        var conf = optionalEntity.get();
                        var rol = new HashSet<Role>();
                        for (RoleEntity role : conf.getPerson().getRoles()) {
                                rol.add(new Role(RoleId.of(UUID.fromString(role.getId())), role.getName()));
                        }

                        var u = new Person(PersonId.of(UUID.fromString(conf.getPerson().getId())),
                                        conf.getPerson().getUsername(),
                                        conf.getPerson().getLastName(),
                                        conf.getPerson().getFirstName(),
                                        conf.getPerson().getEmail(),
                                        conf.getPerson().getPhone(),
                                        conf.getPerson().getPassword());
                        u.setRoles(rol);
                        
                        return new ConfirmationToken(ConfirmationTokenId.of(UUID.fromString(conf.getId())),
                                        conf.getToken(), conf.getCreatedAt(), conf.getExpiresAt(),
                                        conf.getConfirmedAt(), u);

                }
                return null;
        }

        @Override
        public void setConfirmedAt(ConfirmationToken confirmationToken) {
                var rol = new HashSet<RoleEntity>();
                for (Role role : confirmationToken.getPerson().getRoles()) {
                        rol.add(new RoleEntity(role.getRoleId().value(), role.getName()));
                }

                var u = new PersonEntity(confirmationToken.getPerson().getPersonId().value(),
                                confirmationToken.getPerson().getUsername(),
                                confirmationToken.getPerson().getLastName(),
                                confirmationToken.getPerson().getFirstName(),
                                confirmationToken.getPerson().getEmail(),
                                confirmationToken.getPerson().getPhone(),
                                confirmationToken.getPerson().getPassword());
                u.setRoles(rol);

                var conf = new ConfirmationTokenEntity(confirmationToken.getConfirmationTokenId().value(),
                                confirmationToken.getToken(), confirmationToken.getCreatedAt(),
                                confirmationToken.getExpiresAt(),
                                confirmationToken.getConfirmedAt(), u);
                conf.setConfirmedAt(LocalDateTime.now());

                confirmationTokenRepository.save(conf);
        }

        @Override
        public void updateToken(ConfirmationToken confirmationToken, String token) {
                var rol = new HashSet<RoleEntity>();
                for (Role role : confirmationToken.getPerson().getRoles()) {
                        rol.add(new RoleEntity(role.getRoleId().value(), role.getName()));
                }
                var u = new PersonEntity(confirmationToken.getPerson().getPersonId().value(),
                                confirmationToken.getPerson().getUsername(),
                                confirmationToken.getPerson().getLastName(),
                                confirmationToken.getPerson().getFirstName(),
                                confirmationToken.getPerson().getEmail(),
                                confirmationToken.getPerson().getPhone(),
                                confirmationToken.getPerson().getPassword());
                u.setRoles(rol);

                var conf = new ConfirmationTokenEntity(confirmationToken.getConfirmationTokenId().value(),
                                token, confirmationToken.getCreatedAt(), LocalDateTime.now().plusMinutes(15),
                                confirmationToken.getConfirmedAt(), u);

                confirmationTokenRepository.save(conf);
        }

        @Override
        public void saveConfirmationToken(Person person, String token) {

                var rol = new HashSet<RoleEntity>();
                for (Role role : person.getRoles()) {
                        rol.add(new RoleEntity(role.getRoleId().value(), role.getName()));
                }

                var u = new PersonEntity(person.getPersonId().value(),
                                person.getUsername(),
                                person.getLastName(),
                                person.getFirstName(),
                                person.getEmail(),
                                person.getPhone(),
                                person.getPassword());
                u.setRoles(rol);

                var confirmationToken = new ConfirmationTokenEntity(nextId().value(), token, LocalDateTime.now(),
                                LocalDateTime.now().plusMinutes(15), u);

                confirmationTokenRepository.save(confirmationToken);
        }

        @Override
        public ConfirmationToken findByPerson(Person person) {
                var pEn = new PersonEntity(person.getPersonId().value(),
                                person.getUsername(),
                                person.getLastName(),
                                person.getFirstName(),
                                person.getEmail(),
                                person.getPhone(),
                                person.getPassword());

                final Optional<ConfirmationTokenEntity> optionalEntity = confirmationTokenRepository.findByPerson(pEn);

                if (optionalEntity.isPresent()) {
                        var conf = optionalEntity.get();

                        var u = new Person(PersonId.of(UUID.fromString(conf.getPerson().getId())),
                                        conf.getPerson().getUsername(),
                                        conf.getPerson().getLastName(),
                                        conf.getPerson().getFirstName(),
                                        conf.getPerson().getEmail(),
                                        conf.getPerson().getPhone(),
                                        conf.getPerson().getPassword());

                        return new ConfirmationToken(ConfirmationTokenId.of(UUID.fromString(conf.getId())),
                                        conf.getToken(), conf.getCreatedAt(), conf.getExpiresAt(),
                                        conf.getConfirmedAt(), u);

                }
                return null;

        }

        @Override
        public ConfirmationTokenId nextId() {
                return ConfirmationTokenId.of(UUID.randomUUID());
        }
}
