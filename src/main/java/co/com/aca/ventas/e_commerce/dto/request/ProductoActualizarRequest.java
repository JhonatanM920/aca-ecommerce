package co.com.aca.ventas.e_commerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoActualizarRequest(
        @NotNull(message = "Debe ingrsar el id")
        Long id,
        String nombre,

        String descripcion,

        String imagen,

        Double precio
) {
}
