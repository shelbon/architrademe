package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Getter
public class PersonDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final String id;

    private final String username;

    private final String lastName;

    private final String firstName;

    private final String email;

    private final Integer phone;

    private final Boolean enable;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public PersonDetailsImpl(String id, String username, String lastName, String firstName, String email, Integer phone,
            Boolean enable, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.enable = enable;
        this.password = password;
        this.authorities = authorities;
    }

    public static PersonDetailsImpl build(Person person) {
        List<GrantedAuthority> authorities = person.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new PersonDetailsImpl(
                person.getPersonId().value(),
                person.getUsername(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getPhone(),
                person.getEnable(),
                person.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PersonDetailsImpl person = (PersonDetailsImpl) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public String toString() {
        return "PersonDetailsImpl{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", enable=" + enable +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
