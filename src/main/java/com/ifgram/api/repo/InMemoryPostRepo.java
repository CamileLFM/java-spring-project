
package com.ifgram.api.repo;

import com.ifgram.api.model.Post;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPostRepo {
    private final ConcurrentHashMap<String, Post> store = new ConcurrentHashMap<>();

    public Post save(Post p){ store.put(p.getId(), p); return p; }
    public Optional<Post> findById(String id){ return Optional.ofNullable(store.get(id)); }
    public void delete(String id){ store.remove(id); }
    public List<Post> list(){ return new ArrayList<>(store.values()); }
}
