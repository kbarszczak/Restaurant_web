package app.controller.auth;

import app.service.auth.AuthService;
import dto.request.LoginRequest;
import dto.request.RefreshRequest;
import dto.request.RegistrationRequest;
import dto.response.LoginResponse;
import dto.response.Wrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static app.controller.utils.ControllerUtil.execute;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Wrapper<LoginResponse>> login(@RequestBody LoginRequest request){
        return execute(() -> authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Wrapper<LoginResponse>> refresh(@RequestBody RefreshRequest request){
        return execute(() -> authService.refresh(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Wrapper<Void>> register(@RequestBody RegistrationRequest request){
        return execute(() -> authService.register(request));
    }

}
