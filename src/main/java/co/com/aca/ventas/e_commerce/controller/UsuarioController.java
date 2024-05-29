package co.com.aca.ventas.e_commerce.controller;

import co.com.aca.ventas.e_commerce.dto.request.UsuarioRequest;
import co.com.aca.ventas.e_commerce.dto.request.UsuarioUpdateRequest;
import co.com.aca.ventas.e_commerce.dto.response.UsuarioResponse;
import co.com.aca.ventas.e_commerce.exception.BadRequestException;
import co.com.aca.ventas.e_commerce.models.Usuario;
import co.com.aca.ventas.e_commerce.service.impl.UsuarioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsuarioController {

    @Autowired
    private UsuarioImpl usuarioImpl;

    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponse> crear(@Valid @RequestBody UsuarioRequest request)
            throws BadRequestException {
        return ResponseEntity.ok(
                this.usuarioImpl.crear(request)
        );
    }

    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioResponse> actualizar(@Valid @RequestBody UsuarioUpdateRequest request)
            throws BadRequestException {
        return ResponseEntity.ok(
                this.usuarioImpl.actualizar(request)
        );
    }
}
