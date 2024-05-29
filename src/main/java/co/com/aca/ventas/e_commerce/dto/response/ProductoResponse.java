package co.com.aca.ventas.e_commerce.dto.response;

import co.com.aca.ventas.e_commerce.models.Producto;

public record ProductoResponse(

        String nombre,

        String descripcion,

        String imagen,

        Double precio,

        int unidades
) {
    public ProductoResponse(Producto producto){
        this(producto.getNombre(), producto.getDescripcion(), producto.getImagen(), producto.getPrecio(),producto.getUnidades());
    }

    public ProductoResponse(ProductoResponse productoResponse) {
        this(productoResponse.nombre(), productoResponse.descripcion(), productoResponse.imagen(), productoResponse.precio(),productoResponse.unidades());
    }
}
