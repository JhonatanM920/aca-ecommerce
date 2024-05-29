package co.com.aca.ventas.e_commerce.service.iface;

import co.com.aca.ventas.e_commerce.dto.request.ProductoActualizarRequest;
import co.com.aca.ventas.e_commerce.dto.request.ProductoRequest;
import co.com.aca.ventas.e_commerce.dto.request.ProductoUnidadesRequest;
import co.com.aca.ventas.e_commerce.dto.response.ProductoResponse;
import co.com.aca.ventas.e_commerce.exception.BadRequestException;

import java.util.List;

public interface IProductoService {

    public ProductoResponse crear(ProductoRequest request) throws BadRequestException;

    public ProductoResponse actualizar(ProductoActualizarRequest request) throws BadRequestException;

    public List<ProductoResponse> listar();

    public ProductoResponse actualizarUnidades(ProductoUnidadesRequest request) throws BadRequestException;

    public List<ProductoResponse> listarActivos();

}
