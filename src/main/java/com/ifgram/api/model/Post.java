
package com.ifgram.api.model;

import java.time.Instant;
import java.util.UUID;

public class Post {
    private String id = UUID.randomUUID().toString();
    private String titulo;
    private String conteudo;
    private Instant dataCriacao = Instant.now();
    private String authorId;

    public Post(){}
    public Post(String titulo, String conteudo, String authorId){
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.authorId = authorId;
    }
    public String getId(){ return id; }
    public String getTitulo(){ return titulo; }
    public void setTitulo(String titulo){ this.titulo = titulo; }
    public String getConteudo(){ return conteudo; }
    public void setConteudo(String conteudo){ this.conteudo = conteudo; }
    public Instant getDataCriacao(){ return dataCriacao; }
    public String getAuthorId(){ return authorId; }
    public void setAuthorId(String authorId){ this.authorId = authorId; }
}
