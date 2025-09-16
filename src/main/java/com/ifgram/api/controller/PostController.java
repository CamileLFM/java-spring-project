
package com.ifgram.api.controller;

import com.ifgram.api.dto.PostDtos.*;
import com.ifgram.api.entity.Post;
import com.ifgram.api.service.PostService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService posts;
    public PostController(PostService posts){ this.posts = posts; }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int limit){
        Page<Post> p = posts.list(page, limit);
        return ResponseEntity.ok(Map.of(
            "items", p.getContent(),
            "page", page,
            "limit", limit,
            "total", p.getTotalElements()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        return posts.get(id)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error","não encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreatePostRequest req, Authentication auth){
        String userId = (String) auth.getPrincipal();
        Post p = posts.create(req.titulo, req.conteudo, userId);
        return ResponseEntity.status(201).body(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @Valid @RequestBody CreatePostRequest req){
        return posts.updatePut(id, req.titulo, req.conteudo)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error","não encontrado")));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable String id, @RequestBody UpdatePostRequest req){
        return posts.updatePatch(id, req.titulo, req.conteudo)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error","não encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        boolean ok = posts.delete(id);
        if (!ok) return ResponseEntity.status(404).body(Map.of("error","não encontrado"));
        return ResponseEntity.noContent().build();
    }
}
