package com.TravelAGency.TravelAgency.User.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours expiration
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();

}

public String generateToken(UserDetails userDetails)
{
    return generateToken(new HashMap<>(),userDetails);
}

public boolean isTokenValid(String Token,UserDetails userDetails)
{
    final String username=extractUserName(Token);
    return (username.equals(userDetails.getUsername()))&& !isTokenExpired(Token);
}

private Claims extractAllClaims(String token)
{
    return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
}

public String  extractUserName(String token)
{
  return
extractClaim(token,Claims::getSubject);}

    public Date extractExpiration(String token)
    {return extractClaim(token,Claims::getExpiration);}

    private boolean isTokenExpired(String token)
    {return extractExpiration(token).before(new Date());}


private<T> T extractClaim(String token, Function<Claims,T> claimsResolvers)
{
    final Claims claims=extractAllClaims(token);
    return claimsResolvers.apply(claims);
}

private Key getSigninKey() {
    byte[] keyBytes = Decoders.BASE64.decode("41F4428472B4B6250655368566D5970337336763979244226452948404D6351");
    return Keys.hmacShaKeyFor(keyBytes);
}
}

