package co.com.aca.ventas.e_commerce.service.iface;

import co.com.aca.ventas.e_commerce.dto.request.AuthenticationRequest;
import co.com.aca.ventas.e_commerce.dto.response.AuthenticationResponse;

public interface IAuthenticationService {

    public AuthenticationResponse login(AuthenticationRequest request);
}
