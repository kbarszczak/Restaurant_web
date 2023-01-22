package app.service.auth;

import dto.request.LoginRequest;
import dto.request.RefreshRequest;
import dto.request.RegistrationRequest;
import dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request) throws Exception;
    LoginResponse refresh(RefreshRequest request) throws Exception;
    Void register(RegistrationRequest request) throws Exception;
    String getUserIdByEmail(String email);
}
