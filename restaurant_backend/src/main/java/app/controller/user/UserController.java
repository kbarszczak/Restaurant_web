package app.controller.user;

import app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import static app.controller.utils.ControllerUtil.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/orders/exist")
    public ResponseEntity<Boolean> hasOrdered(@RequestParam("dishId") String dishId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal() instanceof User user)) throw new BadCredentialsException("User not authenticated");
        return executeRaw(() -> userService.hasOrdered(user.getUsername(), dishId));
    }

    @GetMapping("/orders")
    public ResponseEntity<Order[]> getOrders(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal() instanceof User user)) throw new BadCredentialsException("User not authenticated");
        return executeRaw(() -> userService.getOrders(user.getUsername()));
    }

    @PostMapping("/orders")
    public ResponseEntity<String> addOrder(@RequestParam("dishId") String dishId, @RequestParam("quantity") Integer quantity){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal() instanceof User user)) throw new BadCredentialsException("User not authenticated");
        return executeString(() -> userService.addOrder(user.getUsername(), dishId, quantity));
    }

}
