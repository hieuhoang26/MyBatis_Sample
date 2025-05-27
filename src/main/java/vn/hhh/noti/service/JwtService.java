package vn.hhh.noti.service;

import org.springframework.security.core.userdetails.UserDetails;
import vn.hhh.noti.utils.TokenType;

public interface JwtService {
    String generateToken(UserDetails user);
    String generateRefreshToken(UserDetails user);
    String extractUsername(String token, TokenType type);
    boolean isValid(String token, TokenType type, UserDetails userDetails);
}
