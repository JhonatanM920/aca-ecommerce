package co.com.aca.ventas.e_commerce.exception;

public class RestException extends Exception{
    private ErrorDTO errorDTO;

    public  RestException(){
        super();
    }

    public RestException(ErrorDTO errorDTO){
        super(errorDTO.getError());
        this.errorDTO=errorDTO;
    }

    public RestException(String message){
        super(message);
    }

    public RestException(String message, Exception ex){
        super(message,ex);
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }

    public void setErrorDTO(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }
}
