
package com.ifgram.api.service;

import com.ifgram.api.entity.Post;
import com.ifgram.api.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepository repo;
    public PostService(PostRepository repo){ this.repo = repo; }

    public Page<Post> list(int page, int limit){
        PageRequest pr = PageRequest.of(Math.max(0, page-1), Math.max(1, limit),
                Sort.by(Sort.Direction.DESC, "dataCriacao"));
        return repo.findAll(pr);
    }

    public Optional<Post> get(String id){ return repo.findById(id); }

    public Post create(String titulo, String conteudo, String authorId){
        Post p = new Post(titulo, conteudo, authorId);
        return repo.save(p);
    }

    public Optional<Post> updatePut(String id, String titulo, String conteudo){
        return repo.findById(id).map(p -> {
            p.setTitulo(titulo);
            p.setConteudo(conteudo);
            return repo.save(p);
        });
    }

    public Optional<Post> updatePatch(String id, String titulo, String conteudo){
        return repo.findById(id).map(p -> {
            if (titulo != null) p.setTitulo(titulo);
            if (conteudo != null) p.setConteudo(conteudo);
            return repo.save(p);
        });
    }

    public boolean delete(String id){
        return repo.findById(id).map(p -> { repo.delete(p); return true; }).orElse(false);
    }
}
