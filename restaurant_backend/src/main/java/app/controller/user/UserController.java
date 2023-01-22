package app.controller.user;

import app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static app.controller.utils.ControllerUtil.executeRaw;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/order")
    public ResponseEntity<Boolean> hasOrdered(@RequestParam("dishId") String dishId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal() instanceof User user)) throw new BadCredentialsException("User not authenticated");
        return executeRaw(() -> userService.hasOrdered(user.getUsername(), dishId));
    }

}
