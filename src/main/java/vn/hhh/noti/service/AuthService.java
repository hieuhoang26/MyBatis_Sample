package vn.hhh.noti.service;

import vn.hhh.noti.dto.LogInRequest;
import vn.hhh.noti.dto.ResponseData;
import vn.hhh.noti.dto.SignUpRequest;
import vn.hhh.noti.dto.TokenResponse;

public interface AuthService {
    TokenResponse login(LogInRequest logInRequest);
    ResponseData signUp(SignUpRequest signUpRequest);

}
