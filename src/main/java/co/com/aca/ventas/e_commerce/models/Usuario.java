package co.com.aca.ventas.e_commerce.models;

import co.com.aca.ventas.e_commerce.dto.request.UsuarioUpdateRequest;
import co.com.aca.ventas.e_commerce.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String username;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void update(UsuarioUpdateRequest request) {
        if(request.nombre() != null) this.nombre = request.nombre();
        if(request.apellido() != null) this.apellido = request.apellido();
        if(request.username() != null) this.username = request.username();
        if(request.password() != null) this.password = request.password();

    }

    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities =
                this.role
                        .getPermissions()
                        .stream()
                        .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name()))
                        .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.role.name()));

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
