package app.controller.dish;

import app.service.auth.AuthService;
import app.service.dish.DishService;
import lombok.RequiredArgsConstructor;
import model.Dish;
import model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import static app.controller.utils.ControllerUtil.*;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDish(@PathVariable String id){
        return executeRaw(() ->  dishService.getDish(id));
    }

    @GetMapping
    public ResponseEntity<Dish[]> getDishes(){
        return executeRaw(dishService::getDishes);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Review[]> getDishReviews(@PathVariable String id){
        return executeRaw(() ->  dishService.getDishReviews(id));
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<String> addDishReview(@PathVariable String id, @RequestParam("text") String text, @RequestParam("rating") Integer rating){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth.getPrincipal() instanceof User user)) throw new BadCredentialsException("User not authenticated");
        return executeString(() ->  dishService.addDishReview(
                user.getUsername(),
                id,
                text,
                rating
        ));
    }

    @PostMapping
    public ResponseEntity<String> addDish(@RequestBody Dish dish){
        return executeString(() -> dishService.addDish(dish));
    }

    @PutMapping
    public ResponseEntity<String> editDish(@RequestBody Dish dish){
        return executeString(() -> dishService.editDish(dish));
    }

}
