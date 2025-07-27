package eventticketing.eventease_backend.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String jwtSecret = "MvG1Nf+3CkV3W7P+BbT2bYzGfnK/0drmAF+Fqg2GoFYBRGHXnPoAV0JoMvwRQ6bM0eu3e2HbtgtEqKjWwMZUtA==";
    private final long jwtExpirationMs = 86400000; 

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
