package com.ventas.app.ventasbazar.repositories;

import com.ventas.app.ventasbazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {
    @Query("SELECT f FROM Venta f WHERE f.fecha_venta=?1")
    List<Venta> listaVentas(LocalDate fecha_venta);
}
