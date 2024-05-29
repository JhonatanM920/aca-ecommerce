package co.com.aca.ventas.e_commerce.service.impl;

import co.com.aca.ventas.e_commerce.models.Usuario;
import co.com.aca.ventas.e_commerce.service.iface.IJwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtImpl implements IJwtService {

    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTE;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    public String generateToken(Usuario user, Map<String, Object> claims) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTE * 60 * 1000));

        return Jwts
                .builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                //.headerParam(Header.TYPE,Header.JWT_TYPE)
                .signWith(generateKey())
                .compact();
    }

    @Override
    public String extractUsername(String jwt) {
        return extractAllClaims(jwt)
                .getSubject();
    }

    private Claims extractAllClaims(String jwt){
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    private SecretKey generateKey(){
        byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretAsBytes);

        //byte[] secretKeyBytes = new byte[32];
        //new SecureRandom().nextBytes(secretKeyBytes);
        //return Keys.hmacShaKeyFor(secretKeyBytes);
    }
}
