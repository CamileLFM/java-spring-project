
package com.ifgram.api.model;

import java.time.Instant;
import java.util.UUID;

public class User {
    private String id = UUID.randomUUID().toString();
    private String email;
    private String passwordHash;
    private String telefone;
    private Instant createdAt = Instant.now();

    public User(){}
    public User(String email, String passwordHash, String telefone){
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
}
