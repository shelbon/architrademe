package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Modality;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ModalityId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.PersonId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ProfilId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Role;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.RoleId;

public class PersonPersistenceAdapter implements PersonPort {

    PersonRepository personRepository;

    public PersonPersistenceAdapter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void register(Person person) {
        var rol = new HashSet<RoleEntity>();
        for (Role role : person.getRoles()) {
            rol.add(new RoleEntity(role.getRoleId().value(), role.getName()));
        }

        var per = new PersonEntity(person.getPersonId().value(),
                person.getUsername(), person.getLastName(),
                person.getFirstName(), person.getEmail(), person.getPhone(), person.getPassword());

        per.setRoles(rol);
        personRepository.save(per);
    }

    @Override
    public Person findByUsername(String username) {

        final Optional<PersonEntity> optionalEntity = personRepository.findByUsername(username);
        if (optionalEntity.isPresent()) {
            var p = optionalEntity.get();

            Set<Role> rol = new HashSet<Role>();
            for (RoleEntity role : p.getRoles()) {
                rol.add(new Role(RoleId.of(UUID.fromString(role.getId())), role.getName()));
            }
            if (p.getPersonProfil() != null) {
                Set<Modality> mod = new HashSet<Modality>();
                for (ModalityEntity mEntity : p.getPersonProfil().getModalities()) {
                    mod.add(new Modality(ModalityId.of(UUID.fromString(mEntity.getId())),
                            mEntity.getName(), mEntity.getCreatedAt(), mEntity.getUpdatedAt()));
                }

                var per = new Profil(ProfilId.of(UUID.fromString(p.getPersonProfil().getId())),
                        p.getPersonProfil().getDisponibility(), p.getPersonProfil().getTjm(),
                        p.getPersonProfil().getCreatedAt(), p.getPersonProfil().getUpdatedAt(),
                        mod);

                return new Person(PersonId.of(UUID.fromString(p.getId())),
                        p.getUsername(), p.getLastName(), p.getFirstName(), p.getEmail(),
                        p.getPhone(), p.getPassword(), rol, p.getEnable(), per, p.getCreatedAt(),
                        p.getUpdatedAt());
            }
            return new Person(PersonId.of(UUID.fromString(p.getId())),
                    p.getUsername(), p.getLastName(), p.getFirstName(), p.getEmail(),
                    p.getPhone(), p.getPassword(), rol, p.getEnable(), p.getCreatedAt(),
                    p.getUpdatedAt());

        }
        return null;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return personRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    @Override
    public String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
                +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n"
                +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n"
                +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
                +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name
                + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
                + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    @Override
    public void enablePerson(Person person) {

        var rol = new HashSet<RoleEntity>();
        for (Role role : person.getRoles()) {
            rol.add(new RoleEntity(role.getRoleId().value(), role.getName()));
        }

        var per = new PersonEntity(person.getPersonId().value(),
                person.getUsername(), person.getLastName(),
                person.getFirstName(), person.getEmail(), person.getPhone(), person.getPassword());
        per.setRoles(rol);
        per.setEnable(true);
        personRepository.save(per);
    }

    @Override
    public Person findById(String id) {

        Optional<PersonEntity> optionalEntity = personRepository.findById(id);

        if (optionalEntity.isPresent()) {
            var p = optionalEntity.get();

            Set<Role> rol = new HashSet<Role>();
            for (RoleEntity role : p.getRoles()) {
                rol.add(new Role(RoleId.of(UUID.fromString(role.getId())), role.getName()));
            }

            if (p.getPersonProfil() != null) {

                Set<Modality> mod = new HashSet<Modality>();

                for (ModalityEntity mEntity : p.getPersonProfil().getModalities()) {
                    mod.add(new Modality(ModalityId.of(UUID.fromString(mEntity.getId())),
                            mEntity.getName(), mEntity.getCreatedAt(), mEntity.getUpdatedAt()));
                }

                var per = new Profil(ProfilId.of(UUID.fromString(p.getPersonProfil().getId())),
                        p.getPersonProfil().getDisponibility(), p.getPersonProfil().getTjm(),
                        p.getPersonProfil().getCreatedAt(), p.getPersonProfil().getUpdatedAt(),
                        mod);

                return new Person(PersonId.of(UUID.fromString(p.getId())),
                        p.getUsername(), p.getLastName(), p.getFirstName(), p.getEmail(),
                        p.getPhone(), p.getPassword(), rol, p.getEnable(), per, p.getCreatedAt(),
                        p.getUpdatedAt());
            }

            return new Person(PersonId.of(UUID.fromString(p.getId())),
                    p.getUsername(), p.getLastName(), p.getFirstName(), p.getEmail(),
                    p.getPhone(), p.getPassword(), rol, p.getEnable(), p.getCreatedAt(),
                    p.getUpdatedAt());
        }
        return null;
    }

    @Override
    public Person findByEmail(String email) {
        final Optional<PersonEntity> optionalEntity = personRepository.findByEmail(email);
        if (optionalEntity.isPresent()) {
            var p = optionalEntity.get();

            Set<Role> rol = new HashSet<Role>();
            for (RoleEntity role : p.getRoles()) {
                rol.add(new Role(RoleId.of(UUID.fromString(role.getId())), role.getName()));
            }
            if (p.getPersonProfil() != null) {
                Set<Modality> mod = new HashSet<Modality>();
                for (ModalityEntity mEntity : p.getPersonProfil().getModalities()) {
                    mod.add(new Modality(ModalityId.of(UUID.fromString(mEntity.getId())),
                            mEntity.getName(), mEntity.getCreatedAt(), mEntity.getUpdatedAt()));
                }

                var per = new Profil(ProfilId.of(UUID.fromString(p.getPersonProfil().getId())),
                        p.getPersonProfil().getDisponibility(), p.getPersonProfil().getTjm(),
                        p.getPersonProfil().getCreatedAt(), p.getPersonProfil().getUpdatedAt(),
                        mod);

                return new Person(PersonId.of(UUID.fromString(p.getId())),
                        p.getUsername(), p.getLastName(), p.getFirstName(), p.getEmail(),
                        p.getPhone(), p.getPassword(), rol, p.getEnable(), per, p.getCreatedAt(),
                        p.getUpdatedAt());
            }
            return new Person(PersonId.of(UUID.fromString(p.getId())),
                    p.getUsername(), p.getLastName(), p.getFirstName(), p.getEmail(),
                    p.getPhone(), p.getPassword(), rol, p.getEnable(), p.getCreatedAt(),
                    p.getUpdatedAt());
        }
        return null;
    }
}
