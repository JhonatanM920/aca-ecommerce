package co.com.aca.ventas.e_commerce.config;


import co.com.aca.ventas.e_commerce.exception.BadRequestException;
import co.com.aca.ventas.e_commerce.exception.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private static  final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorDTO> getBadRequestException(BadRequestException exception){
        LOGGER.info(exception.getErrorDTO().getMessage());
        return new ResponseEntity<>(exception.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }
}
