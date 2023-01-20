package app.controller.utils;

import dto.response.Wrapper;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

public class ControllerUtil {

    private ControllerUtil(){}

    public static <R> ResponseEntity<Wrapper<R>> execute(Executable<R> executable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new Wrapper<>(executable.execute(), "Success"));
        } catch (IllegalStateException | IllegalArgumentException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Wrapper<>(null, exception.getMessage()));
        } catch (JwtException | BadCredentialsException exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Wrapper<>(null, exception.getMessage()));
        } catch (Exception exception){
            System.out.println("Exception name: '" + exception.getClass().getName() + "'");
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Wrapper<>(null, exception.getMessage()));
        }
    }

}
