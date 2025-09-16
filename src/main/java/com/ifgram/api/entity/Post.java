
package com.ifgram.api.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(nullable = false)
    private Instant dataCriacao = Instant.now();

    @Column(nullable = false, length = 36)
    private String authorId;

    public Post(){}
    public Post(String titulo, String conteudo, String authorId){
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.authorId = authorId;
    }

    public String getId(){ return id; }
    public String getTitulo(){ return titulo; }
    public void setTitulo(String t){ this.titulo = t; }
    public String getConteudo(){ return conteudo; }
    public void setConteudo(String c){ this.conteudo = c; }
    public Instant getDataCriacao(){ return dataCriacao; }
    public String getAuthorId(){ return authorId; }
    public void setAuthorId(String a){ this.authorId = a; }
}
