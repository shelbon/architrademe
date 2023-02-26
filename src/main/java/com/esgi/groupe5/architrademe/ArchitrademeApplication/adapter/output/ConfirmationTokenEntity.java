package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirm_tokens")
@Data
public class ConfirmationTokenEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "person_id")
    private PersonEntity person;

    public ConfirmationTokenEntity(String id, String token, LocalDateTime createdAt,
            LocalDateTime expiresAt, PersonEntity person) {
        this.id = id;
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.person = person;
    }

}
