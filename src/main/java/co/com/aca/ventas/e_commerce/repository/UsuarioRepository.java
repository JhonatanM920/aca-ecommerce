package co.com.aca.ventas.e_commerce.repository;

import co.com.aca.ventas.e_commerce.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByUsername(String nombreUsuario);

    Optional<Usuario> findByUsername(String username);

}
