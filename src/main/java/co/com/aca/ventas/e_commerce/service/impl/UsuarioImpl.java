package co.com.aca.ventas.e_commerce.service.impl;

import co.com.aca.ventas.e_commerce.dto.request.UsuarioRequest;
import co.com.aca.ventas.e_commerce.dto.request.UsuarioUpdateRequest;
import co.com.aca.ventas.e_commerce.dto.response.UsuarioResponse;
import co.com.aca.ventas.e_commerce.exception.BadRequestException;
import co.com.aca.ventas.e_commerce.exception.ErrorDTO;
import co.com.aca.ventas.e_commerce.models.Usuario;
import co.com.aca.ventas.e_commerce.repository.UsuarioRepository;
import co.com.aca.ventas.e_commerce.service.iface.UsuarioService;
import co.com.aca.ventas.e_commerce.util.ResponseReturn;
import co.com.aca.ventas.e_commerce.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioImpl implements UsuarioService {

    @Lazy
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse crear(UsuarioRequest request) throws BadRequestException {
        if(this.usuarioRepository.existsByUsername(request.username())){
            throw new BadRequestException(
                    ErrorDTO.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message("El usuario ingresado ya existe")
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre());
        usuario.setApellido(request.apellido());
        usuario.setUsername(request.username());
        usuario.setPassword(this.passwordEncoder.encode(request.password()));
        usuario.setRole(Role.CUSTOMER);

        this.usuarioRepository.save(usuario);

       return ResponseReturn.returnUsuarioResponse(usuario);
    }

    @Override
    public UsuarioResponse actualizar(UsuarioUpdateRequest request) throws BadRequestException {
        if(!this.usuarioRepository.existsById(request.id())){
            throw new BadRequestException(
                    ErrorDTO.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message("El id ingresado no existe")
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

        Usuario usuario = this.usuarioRepository.findById(request.id()).get();

        setPasswordUser(request, usuario);
        usuario.update(request);

        this.usuarioRepository.save(usuario);

        return ResponseReturn.returnUsuarioResponse(usuario);
    }


    private void setPasswordUser(UsuarioUpdateRequest userUpdateRequest, Usuario user) {
        if(userUpdateRequest.password()!=null){
            user.setPassword(this.passwordEncoder.encode(userUpdateRequest.password()));
        }
    }
}
