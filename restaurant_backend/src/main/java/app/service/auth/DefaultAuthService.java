package app.service.auth;

import app.dao.UserDao;
import app.security.JWTUtil;
import dto.request.LoginRequest;
import dto.request.RefreshRequest;
import dto.request.RegistrationRequest;
import dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService{

    private final long tokenValidForMs = TimeUnit.MINUTES.toMillis(60);
    private final long refreshTokenValidForMs = TimeUnit.HOURS.toMillis(5);

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public LoginResponse login(LoginRequest request) throws Exception{
        if(request == null) throw new IllegalArgumentException("The body was not provided");
        if(request.getEmail() == null || request.getEmail().isEmpty()) throw new IllegalArgumentException("The email is either null or empty string");
        if(request.getPassword() == null || request.getPassword().isEmpty()) throw new IllegalArgumentException("The password is either null or empty string");

        String email = request.getEmail().toLowerCase();
        String password = request.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        Optional<User> user = userDao.findByEmailEquals(email);
        if(user.isEmpty()) throw new IllegalStateException("The used with email '" + email + "' does not exist");

        UserDetails userDetails = userDao.loadUserByUsername(email);
        if(userDetails == null) throw new Exception("Cannot generate token. The unknown exception occurred");

        return new LoginResponse(
                jwtUtil.generateToken(userDetails, tokenValidForMs),
                jwtUtil.generateToken(userDetails, refreshTokenValidForMs),
                extractRoles(userDetails.getAuthorities()),
                user.get().getName(),
                user.get().getSurname()
        );
    }

    @Override
    public LoginResponse refresh(RefreshRequest request) throws Exception{
        if(request == null) throw new IllegalArgumentException("The body was not provided");
        if(request.getRefreshToken() == null || request.getRefreshToken().isEmpty()) throw new IllegalArgumentException("The refresh token is either null or empty string");

        String email = jwtUtil.extractUsername(request.getRefreshToken());
        UserDetails userDetails = userDao.loadUserByUsername(email);

        Optional<User> user = userDao.findByEmailEquals(email);
        if(user.isEmpty()) throw new IllegalStateException("The used with email '" + email + "' does not exist");

        return new LoginResponse(
                jwtUtil.generateToken(userDetails, tokenValidForMs),
                request.getRefreshToken(),
                extractRoles(userDetails.getAuthorities()),
                user.get().getName(),
                user.get().getSurname()
        );
    }

    @Override
    public Void register(RegistrationRequest request) throws Exception{
        if(request == null) throw new IllegalArgumentException("The body was not provided");
        if(request.getName() == null || request.getName().isEmpty()) throw new IllegalArgumentException("The name is either null or empty string");
        if(request.getSurname() == null || request.getSurname().isEmpty()) throw new IllegalArgumentException("The surname is either null or empty string");
        if(request.getPassword() == null || request.getPassword().isEmpty()) throw new IllegalArgumentException("The password is either null or empty string");
        if(request.getEmail() == null || request.getEmail().isEmpty()) throw new IllegalArgumentException("The email is either null or empty string");

        String name = request.getName().toLowerCase();
        String surname = request.getSurname().toLowerCase();
        String email = request.getEmail().toLowerCase();
        String password = encoder.encode(request.getPassword());

        if(!email.matches("^[a-zA-Z0-9_!#$%&'*+\\/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) throw new IllegalArgumentException("Invalid email");
        if(userDao.findByEmailEquals(email).isPresent()) throw new IllegalStateException("The user with email '" + email + "' already exist");

        User user = new User(
                name,
                surname,
                email,
                password,
                new String[]{"ROLE_USER"}
        );
        userDao.insert(user);
        return null;
    }

    @Override
    public String getUserIdByEmail(String email) {
        Optional<User> user = userDao.findByEmailEquals(email);
        if(user.isEmpty()) throw new IllegalArgumentException("The user with email '" + email + "' does not exist");
        return user.get().getId();
    }

    private static String[] extractRoles(Collection<? extends GrantedAuthority> authorities){
        String []roles = new String[authorities.size()];
        int index = 0;
        for(GrantedAuthority authority : authorities){
            if(authority instanceof SimpleGrantedAuthority simpleGrantedAuthority){
                roles[index] = simpleGrantedAuthority.getAuthority();
            }
        }
        return roles;
    }
}
