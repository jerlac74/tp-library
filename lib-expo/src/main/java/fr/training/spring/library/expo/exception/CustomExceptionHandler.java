package fr.training.spring.library.expo.exception;

import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);
/*
    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleExceptionNotFound(RuntimeException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
*/
    //on peut gérer l'exception de cette manière aussi pour avoir notre code erreur personnalisé
    @ResponseBody
    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> libNotFound(NotFoundException ex){
        String errorCode = ex.getErrorCodes();
        LOG.info("Erreur {} {}", errorCode, ex.getMessage());

        return new ResponseEntity<String>(errorCode, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> libNotFound(Exception ex){
        String errorCode = ErrorCodes.EXCEPTION_UNKNOWN;
        LOG.info("Erreur {} {}", errorCode, ex.getMessage());

        return new ResponseEntity<String>(errorCode, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= {MethodArgumentNotValidException.class})
    public Map<String, String> handleMethodArgValidationExceptions(MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
