package com.ventas.app.ventasbazar.service;

import com.ventas.app.ventasbazar.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IProductoService {

    List<Producto> listaProductos();
    Optional<Producto> buscarCodigo(Long codigo);

    Producto ingresarProducto(Producto producto);
    Optional<Producto> editarProducto(Long codigo, Producto producto);

    List<Producto> listaStock();
}
