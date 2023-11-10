package club.pard.server.assignment05.security;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenProvider {
    private static final Key SECURITY_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String create(String userEmail)
    {
        Date expirationTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        return Jwts.builder()
            .signWith(SECURITY_KEY, SignatureAlgorithm.HS512)
            .setSubject(userEmail).setIssuedAt(new Date()).setExpiration(expirationTime)
            .compact();
    }

    public String validate(String token)
    {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(SECURITY_KEY).build()
            .parseClaimsJws(token).getBody();
        // Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}
