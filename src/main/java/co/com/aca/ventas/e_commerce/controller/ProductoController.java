package co.com.aca.ventas.e_commerce.controller;

import co.com.aca.ventas.e_commerce.dto.request.ProductoActualizarRequest;
import co.com.aca.ventas.e_commerce.dto.request.ProductoRequest;
import co.com.aca.ventas.e_commerce.dto.request.ProductoUnidadesRequest;
import co.com.aca.ventas.e_commerce.dto.response.ProductoResponse;
import co.com.aca.ventas.e_commerce.exception.BadRequestException;
import co.com.aca.ventas.e_commerce.service.impl.ProductoImpl;
import co.com.aca.ventas.e_commerce.service.impl.UsuarioImpl;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoImpl producto;

    @PostMapping("/crear")
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request)
            throws BadRequestException {
        return  ResponseEntity.ok(
                this.producto.crear(request)
        );
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ProductoResponse> actualizar(@Valid @RequestBody ProductoActualizarRequest request)
            throws BadRequestException {
        return  ResponseEntity.ok(
                this.producto.actualizar(request)
        );
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoResponse>> listarTodos() {
        return  ResponseEntity.ok(
                this.producto.listar()
        );
    }

    @PutMapping("/actualizar-unidades")
    public ResponseEntity<ProductoResponse> actualizarUnidades(@Valid @RequestBody ProductoUnidadesRequest request)
            throws BadRequestException {
        return ResponseEntity.ok(
                this.producto.actualizarUnidades(request)
        );
    }

    @GetMapping("/listar-activos")
    public ResponseEntity<List<ProductoResponse>> listarTodosActivos() {
        return  ResponseEntity.ok(
                this.producto.listarActivos()
        );
    }
}
