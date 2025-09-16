
package com.ifgram.api.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${app.auth.secret:change-me}")
    private String secret;
    @Value("${app.auth.ttlSeconds:3600}")
    private long ttlSeconds;

    private String sign(String data){
        try{
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        }catch(Exception e){ throw new RuntimeException(e); }
    }

    public String issue(String userId){
        long exp = Instant.now().getEpochSecond() + ttlSeconds;
        String payload = userId + "." + exp;
        String token = payload + "." + sign(payload);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token.getBytes(StandardCharsets.UTF_8));
    }

    public Optional<String> verify(String tokenEncoded){
        try{
            String token = new String(Base64.getUrlDecoder().decode(tokenEncoded), StandardCharsets.UTF_8);
            String[] parts = token.split("\.");
            if (parts.length != 3) return Optional.empty();
            String payload = parts[0] + "." + parts[1];
            if (!sign(payload).equals(parts[2])) return Optional.empty();
            long exp = Long.parseLong(parts[1]);
            if (exp < Instant.now().getEpochSecond()) return Optional.empty();
            return Optional.of(parts[0]);
        }catch(Exception e){ return Optional.empty(); }
    }

    public long getTtlSeconds(){ return ttlSeconds; }
}
