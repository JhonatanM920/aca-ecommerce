package co.com.aca.ventas.e_commerce.service.iface;

import co.com.aca.ventas.e_commerce.models.Usuario;

import java.util.Map;

public interface IJwtService {

    public String generateToken(Usuario user, Map<String,Object> claims);

    public String extractUsername(String jwt);

}
