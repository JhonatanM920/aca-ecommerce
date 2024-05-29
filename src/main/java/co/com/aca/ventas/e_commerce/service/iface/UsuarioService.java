package co.com.aca.ventas.e_commerce.service.iface;

import co.com.aca.ventas.e_commerce.dto.request.UsuarioRequest;
import co.com.aca.ventas.e_commerce.dto.request.UsuarioUpdateRequest;
import co.com.aca.ventas.e_commerce.dto.response.UsuarioResponse;
import co.com.aca.ventas.e_commerce.exception.BadRequestException;

public interface UsuarioService {
    public UsuarioResponse crear(UsuarioRequest request) throws BadRequestException;

    public UsuarioResponse actualizar(UsuarioUpdateRequest request) throws BadRequestException;


}
