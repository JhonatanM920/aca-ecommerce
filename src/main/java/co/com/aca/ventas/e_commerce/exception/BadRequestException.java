package co.com.aca.ventas.e_commerce.exception;

public class BadRequestException extends RestException{

    public  BadRequestException(){
        super();
    }

    public BadRequestException(ErrorDTO errorDTO){
        super(errorDTO);
    }

    public BadRequestException(String message){
        super(message);
    }

}
