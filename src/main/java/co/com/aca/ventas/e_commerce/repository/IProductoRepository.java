package co.com.aca.ventas.e_commerce.repository;

import co.com.aca.ventas.e_commerce.dto.response.ProductoResponse;
import co.com.aca.ventas.e_commerce.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Long> {
    boolean existsByNombre(String nombre);

    List<Producto> findByEstaActivoTrue();
}
