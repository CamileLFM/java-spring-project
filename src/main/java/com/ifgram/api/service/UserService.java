
package com.ifgram.api.service;

import com.ifgram.api.entity.User;
import com.ifgram.api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder){
        this.repo = repo; this.encoder = encoder;
    }

    public Optional<User> findByEmail(String email){ return repo.findByEmail(email.toLowerCase()); }

    public User signup(String email, String rawPassword, String telefone){
        if (repo.findByEmail(email.toLowerCase()).isPresent()){
            throw new IllegalStateException("email já cadastrado");
        }
        String hash = encoder.encode(rawPassword);
        User u = new User(email.toLowerCase(), hash, telefone);
        return repo.save(u);
    }

    public Optional<User> validatePassword(String email, String rawPassword){
        var u = repo.findByEmail(email.toLowerCase());
        if (u.isPresent() && encoder.matches(rawPassword, u.get().getPasswordHash())){
            return u;
        }
        return Optional.empty();
    }
}
