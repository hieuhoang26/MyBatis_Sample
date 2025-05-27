package vn.hhh.noti.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.hhh.noti.dto.LogInRequest;
import vn.hhh.noti.dto.ResponseData;
import vn.hhh.noti.dto.SignUpRequest;
import vn.hhh.noti.dto.TokenResponse;
import vn.hhh.noti.model.User;
import vn.hhh.noti.repository.UserMapper;
import vn.hhh.noti.service.AuthService;
import vn.hhh.noti.service.JwtService;
import vn.hhh.noti.utils.Role;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    final UserMapper userMapper;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final JwtService jwtService;
    @Override
    public TokenResponse login(LogInRequest request) {
//        Authen
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User  user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        String role =user.getAuthorities().toString();
        TokenResponse.TokenResponseBuilder responseBuilder = TokenResponse.builder()
                .id(user.getId())
                .role(role)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .message("Login success");
        return responseBuilder.build();
    }

    @Override
    public ResponseData signUp(SignUpRequest request) {
        // check exist
        if(userMapper.existsByUsername(request.getUsername())){
            return new ResponseData(HttpStatus.BAD_REQUEST.value(), "Usernames is already taken!");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .isActive(true)
                .build();
        userMapper.saveUser(user);
        return new ResponseData(HttpStatus.OK.value(), "Sign Up successful");
    }
}
