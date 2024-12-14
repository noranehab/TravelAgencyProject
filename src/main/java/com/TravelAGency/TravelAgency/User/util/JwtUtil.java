package com.TravelAGency.TravelAgency.User.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
private String generateToken(Map<String, Object> extraClaims, UserDetails details) {
    return Jwts.builder().setClaims(extraClaims).setSubject(details.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
            .signWith(getSigninKey(), SignatureAlgorithm.HS256)
            .compact();

}

private Key getSigninKey() {
    byte[] keyBytes = Decoders.BASE64.decode("41F4428472B4B6250655368566D5970337336763979244226452948404D6351");
    return Keys.hmacShaKeyFor(keyBytes);
}
}

