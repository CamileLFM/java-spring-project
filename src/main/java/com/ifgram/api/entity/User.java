
package com.ifgram.api.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users", indexes = { @Index(name = "uk_users_email", columnList = "email", unique = true) })
public class User {
    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, length = 200)
    private String passwordHash;

    @Column(nullable = false, length = 30)
    private String telefone;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    public User() {}
    public User(String email, String passwordHash, String telefone) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.telefone = telefone;
    }

    public String getId(){ return id; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
    public String getPasswordHash(){ return passwordHash; }
    public void setPasswordHash(String passwordHash){ this.passwordHash = passwordHash; }
    public String getTelefone(){ return telefone; }
    public void setTelefone(String telefone){ this.telefone = telefone; }
    public Instant getCreatedAt(){ return createdAt; }
    public void setCreatedAt(Instant createdAt){ this.createdAt = createdAt; }
}
