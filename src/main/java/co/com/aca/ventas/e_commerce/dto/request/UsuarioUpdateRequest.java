package co.com.aca.ventas.e_commerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioUpdateRequest(
        @NotNull(message = "Debe ingresar un id")
        Long id,
        String nombre,
        String apellido,
        String username,
        String password
) {
}
