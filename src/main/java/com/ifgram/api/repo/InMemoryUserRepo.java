
package com.ifgram.api.repo;

import com.ifgram.api.model.User;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepo {
    private final ConcurrentHashMap<String, User> byId = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, User> byEmail = new ConcurrentHashMap<>();

    public Optional<User> findByEmail(String email){
        return Optional.ofNullable(byEmail.get(email.toLowerCase()));
    }
    public Optional<User> findById(String id){
        return Optional.ofNullable(byId.get(id));
    }
    public User save(User u){
        byId.put(u.getId(), u);
        byEmail.put(u.getEmail().toLowerCase(), u);
        return u;
    }
}
