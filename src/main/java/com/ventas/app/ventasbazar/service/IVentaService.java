package com.ventas.app.ventasbazar.service;



import com.ventas.app.ventasbazar.model.Producto;
import com.ventas.app.ventasbazar.model.Venta;
import com.ventas.app.ventasbazar.model.VentaDto;
import com.ventas.app.ventasbazar.model.VentaMayorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface IVentaService {

    List<Venta> listaVenta();

    Optional<Venta> buscarVenta(Long codigoVenta);

    Venta crearVenta(Venta venta);

    Optional<Venta> eliminarVenta(Long codigoVenta);

    Optional<Venta> modificarVenta(Long codigoVenta, Venta venta);
    List<Producto> listaDeProductos(Long codigoVenta );

    @Query ("SELECT f FROM Venta f WHERE f.fecha_venta=?1")
    VentaDto listaVentas(LocalDate fecha_venta);

    VentaMayorDto ventaMayor();
}
