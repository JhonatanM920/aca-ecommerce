package co.com.aca.ventas.e_commerce.service.impl;

import co.com.aca.ventas.e_commerce.dto.request.AuthenticationRequest;
import co.com.aca.ventas.e_commerce.dto.response.AuthenticationResponse;
import co.com.aca.ventas.e_commerce.models.Usuario;
import co.com.aca.ventas.e_commerce.repository.UsuarioRepository;
import co.com.aca.ventas.e_commerce.service.iface.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationImpl implements IAuthenticationService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtImpl jwtImpl;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            request.getUsername(),request.getPassword()
        );

        this.authenticationManager.authenticate(authToken);

        Usuario user = this.userRepository.findByUsername(request.getUsername()).get();

        String jwt = this.jwtImpl.generateToken(user,generateExtractClaims(user));

        return new AuthenticationResponse(jwt);
    }

    private Map<String,Object> generateExtractClaims(Usuario user) {
        Map<String,Object> extrcatClaims = new HashMap<>();
        extrcatClaims.put("name",user.getNombre());
        extrcatClaims.put("role",user.getRole().name());
        extrcatClaims.put("permission",user.getAuthorities());

        return extrcatClaims;
    }
}
