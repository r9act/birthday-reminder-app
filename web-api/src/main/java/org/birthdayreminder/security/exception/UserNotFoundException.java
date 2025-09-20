package org.birthdayreminder.security.exception;/**
 * @author a.mishkin
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* Кастомное исключение, чтобы его перехватил link{#GlobalExceptionHandler}
*/
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
