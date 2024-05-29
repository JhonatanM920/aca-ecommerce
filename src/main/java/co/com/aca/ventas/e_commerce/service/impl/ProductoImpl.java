package co.com.aca.ventas.e_commerce.service.impl;

import co.com.aca.ventas.e_commerce.dto.request.ProductoActualizarRequest;
import co.com.aca.ventas.e_commerce.dto.request.ProductoRequest;
import co.com.aca.ventas.e_commerce.dto.request.ProductoUnidadesRequest;
import co.com.aca.ventas.e_commerce.dto.response.ProductoResponse;
import co.com.aca.ventas.e_commerce.exception.BadRequestException;
import co.com.aca.ventas.e_commerce.exception.ErrorDTO;
import co.com.aca.ventas.e_commerce.models.Producto;
import co.com.aca.ventas.e_commerce.repository.IProductoRepository;
import co.com.aca.ventas.e_commerce.repository.UsuarioRepository;
import co.com.aca.ventas.e_commerce.service.iface.IProductoService;
import co.com.aca.ventas.e_commerce.util.ResponseReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoImpl implements IProductoService {

    @Autowired
    private IProductoRepository iProductoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ProductoResponse crear(ProductoRequest request) throws BadRequestException {

        if(this.iProductoRepository.existsByNombre(request.nombre())){
            throw new BadRequestException(
                    ErrorDTO.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message("El Producto  ingresado ya existe")
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

        if(request.unidades() < 0){
            throw new BadRequestException(
                    ErrorDTO.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message("No es posible ingresar numeros negativos")
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

        var usurio = this.usuarioRepository.getReferenceById(request.usuario());

        var producto = new Producto();


        producto.setUsuario(usurio);
        producto.setNombre(request.nombre());
        producto.setDescripcion(request.descripcion());
        producto.setImagen(request.imagen());
        producto.setPrecio(request.precio());
        producto.setUnidades(request.unidades());
        productoActivo(request.unidades(), producto);

        this.iProductoRepository.save(producto);

        return ResponseReturn.returnProductoResponse(producto);
    }


    @Override
    public ProductoResponse actualizar(ProductoActualizarRequest request) throws BadRequestException {
        if(this.iProductoRepository.existsByNombre(request.nombre())){
            throw new BadRequestException(
                    ErrorDTO.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message("El Producto  ingresado ya existe")
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

        var producto = this.iProductoRepository.findById(request.id()).get();

        producto.actualizar(request);

        this.iProductoRepository.save(producto);

        return ResponseReturn.returnProductoResponse(producto);
    }

    @Override
    public List<ProductoResponse>  listar() {
        return this.iProductoRepository.findAll().stream().map(ProductoResponse::new).toList();
    }

    @Override
    public ProductoResponse actualizarUnidades(ProductoUnidadesRequest request) throws BadRequestException {

        if(!this.iProductoRepository.existsById(request.id())){
            throw new BadRequestException(
                    ErrorDTO.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message("El Producto  ingresado No existe")
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

        if(request.unidades() < 0){
            throw new BadRequestException(
                    ErrorDTO.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message("No es posible ingresar numeros negativos")
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

        var producto = this.iProductoRepository.getReferenceById(request.id());

        producto.setUnidades(request.unidades());

        productoActivo(producto.getUnidades(), producto);

        this.iProductoRepository.save(producto);

        return ResponseReturn.returnProductoResponse(producto);
    }

    @Override
    public List<ProductoResponse> listarActivos() {
        return this.iProductoRepository.findByEstaActivoTrue().stream().map(ProductoResponse::new).toList();
    }


    private void productoActivo(int unidades, Producto producto) {
        if(unidades > 0) {
            producto.setEstaActivo(true);
        }else if(unidades == 0){
            producto.setEstaActivo(false);
        }
    }
}
