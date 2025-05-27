package vn.hhh.noti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.hhh.noti.dto.*;
import vn.hhh.noti.service.AuthService;
import vn.hhh.noti.utils.Uri;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth Controller")
public class AuthController {
    final AuthService authService;
    @Operation(summary = "Login ", description = "Login ")
    @PostMapping(Uri.LOGIN)
    public ResponseData<?> Login(@RequestBody LogInRequest request) {
        try {

            return new ResponseData<>(HttpStatus.CREATED.value(), "Login", authService.login(request));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @Operation(summary = "SignUp ", description = "SignUp ")
    @PostMapping(Uri.SIGNUP)
    public ResponseData<?> SignUp(@RequestBody SignUpRequest request) {
        try {

            return new ResponseData<>(HttpStatus.CREATED.value(), "SignUp", authService.signUp(request));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
