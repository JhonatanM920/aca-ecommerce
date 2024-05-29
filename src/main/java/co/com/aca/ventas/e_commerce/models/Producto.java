package co.com.aca.ventas.e_commerce.models;

import co.com.aca.ventas.e_commerce.dto.request.ProductoActualizarRequest;
import co.com.aca.ventas.e_commerce.dto.request.ProductoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String nombre;

    private String descripcion;

    private String imagen;

    private Boolean estaActivo;

    private Double precio;

    private int unidades;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public void actualizar(ProductoActualizarRequest request) {
        if(request.nombre() !=null) this.nombre = request.nombre();
        if(request.descripcion() !=null) this.descripcion = request.descripcion();
        if(request.imagen() !=null) this.imagen = request.imagen();
        if(request.precio() !=null) this.precio = request.precio();
    }
}
