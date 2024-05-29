package co.com.aca.ventas.e_commerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoRequest(
        @NotNull(message = "debe ingresar el id del usuario")
        Long usuario,
        @NotBlank(message = "Debe ingresar el nombre del producto")
        String nombre,

        @NotBlank(message = "Debe ingresar la descripcion del producto")
        String descripcion,

        String imagen,

        @NotNull(message = "Debe ingresar el precio del producto")
        Double precio,

        @NotNull(message = "Debe ingresar la catidad de productos")
        int unidades
) {
}
