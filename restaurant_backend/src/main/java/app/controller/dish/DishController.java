package app.controller.dish;

import app.service.dish.DishService;
import lombok.RequiredArgsConstructor;
import model.Dish;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static app.controller.utils.ControllerUtil.*;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public ResponseEntity<Dish[]> getDishes(){
        return executeRaw(dishService::getDishes);
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
