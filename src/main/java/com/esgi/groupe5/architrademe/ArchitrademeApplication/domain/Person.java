package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Person {

    private final PersonId personId;

    private String username;

    private String lastName;

    private String firstName;

    private String email;

    private Integer phone;

    @JsonIgnore
    private String password;

    private Set<Role> roles = new HashSet<Role>();

    private Boolean enable = false;

    private Profil profil;

    private Date createdAt;

    private Date updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private Set<Offer> offers = new HashSet<>();

    public Person(PersonId personId, String username, String lastName, String firstName, String email, Integer phone,
            String password) {
        this.personId = personId;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Person(PersonId personId, String username, String lastName, String firstName, String email, Integer phone,
                  String password, Set<Role> roles, Boolean enable, Profil profil, Date createdAt,
                  Date updatedAt) {
        this.personId = personId;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.roles = roles;
        this.enable = enable;
        this.profil = profil;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Person(PersonId personId, String username, String lastName, String firstName, String email, Integer phone,
            String password, Set<Role> roles, Boolean enable, Date createdAt, Date updatedAt) {
        this.personId = personId;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.roles = roles;
        this.enable = enable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }




}
