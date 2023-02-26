package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.PersonDetailsImpl;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.PersonId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Role;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.RoleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonDetailsPersistenceAdapter implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PersonEntity person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Person Not Found with username: " + username));
     

        Set<Role> rol = new HashSet<Role>();
        for (RoleEntity role : person.getRoles()) {
            rol.add(new Role(RoleId.of(UUID.fromString(role.getId())), role.getName()));
        }

        var p = new Person(PersonId.of(UUID.fromString(person.getId())), person.getUsername(),
                person.getLastName(), person.getFirstName(), person.getEmail(),
                person.getPhone(), person.getPassword(), rol,
                person.getEnable(), person.getCreatedAt(), person.getUpdatedAt());

        return PersonDetailsImpl.build(p);
    }

}
