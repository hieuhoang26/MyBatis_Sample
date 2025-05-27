package vn.hhh.noti.service.imp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.hhh.noti.service.JwtService;
import vn.hhh.noti.utils.TokenType;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static vn.hhh.noti.utils.TokenType.ACCESS_TOKEN;
import static vn.hhh.noti.utils.TokenType.REFRESH_TOKEN;

@Service
@Slf4j
public class JwtServiceImp implements JwtService {
    private final long accessEpr = 1000 * 60 * 60 * 24; // 24 hours
    private final long refreshEpr = 1000 * 60 * 60 * 24 * 10; // 10 day

    @Value("${jwt.accessKey}")
    private String accessKey; // sha256

    @Value("${jwt.refreshKey}")
    private String refreshKey; // sha256
    @Override
    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(), user);
    }

    @Override
    public String generateRefreshToken(UserDetails user) {
        return generateRefreshToken(new HashMap<>(), user);
    }

    @Override
    public String extractUsername(String token, TokenType type) {
        return extractClaim(token,type, Claims::getSubject);
    }

    @Override
    public boolean isValid(String token, TokenType type, UserDetails userDetails) {
        String username = extractUsername(token,type);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token,type);
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims) // private info for payload not shown in token
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessEpr))
                .signWith(getKey(ACCESS_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }
    private String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims) // private info for payload not shown in token
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshEpr))
                .signWith(getKey(REFRESH_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }
    //    private Key getKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(accessKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
    private Key getKey(TokenType type) {
        if (ACCESS_TOKEN.equals(type))
            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessKey));
        else
            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshKey));
    }
    private boolean isTokenExpired(String token, TokenType type) {
        return extractExpiration(token,type).before(new Date());
    }

    private Date extractExpiration(String token, TokenType type) {
        return extractClaim(token,type, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, TokenType type, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token,type);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, TokenType type) {
        return Jwts.parser()
                .setSigningKey(getKey(type))
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
}
