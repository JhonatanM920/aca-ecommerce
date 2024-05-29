package co.com.aca.ventas.e_commerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    @NotBlank(message = "Debe ingresar su nombre de usuario")
    private String username;

    @NotBlank(message = "Debe ingresar su contraseña")
    private String password;
}
