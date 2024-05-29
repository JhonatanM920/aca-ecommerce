package co.com.aca.ventas.e_commerce.dto.request;

import jakarta.validation.constraints.NotNull;

public record ProductoUnidadesRequest(
        @NotNull(message = "debe ingresar un id de producto")
        Long id,

        @NotNull(message = "debe ingresar las unidades")
        int unidades
) {
}
