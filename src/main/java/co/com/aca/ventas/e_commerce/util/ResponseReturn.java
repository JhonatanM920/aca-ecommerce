package co.com.aca.ventas.e_commerce.util;

import co.com.aca.ventas.e_commerce.dto.response.ProductoResponse;
import co.com.aca.ventas.e_commerce.dto.response.UsuarioResponse;
import co.com.aca.ventas.e_commerce.models.Producto;
import co.com.aca.ventas.e_commerce.models.Usuario;

public class ResponseReturn {

    public static UsuarioResponse  returnUsuarioResponse(Usuario usuario){
        return new UsuarioResponse(
                usuario.getNombre(),
                usuario.getUsername()
        );
    }

    public static ProductoResponse returnProductoResponse(Producto producto){
        return new ProductoResponse(
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getImagen(),
                producto.getPrecio(),
                producto.getUnidades()
        );
    }
}
