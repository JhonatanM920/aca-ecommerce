package co.com.aca.ventas.e_commerce.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank(message = "Debe ingresar su nombre")
        String nombre,
        @NotBlank(message = "Debe ingresar su apellido")
        String apellido,
        @NotBlank(message = "Debe ingresar su nombre de datos")
        String username,
        @NotBlank(message = "Debe ingresar su contraseña")
        String password
) {
}
