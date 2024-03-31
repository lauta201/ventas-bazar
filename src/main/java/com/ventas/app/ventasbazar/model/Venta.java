package com.ventas.app.ventasbazar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_venta")
    private Long codigoVenta;
    private LocalDate fecha_venta = LocalDate.now();
    private Double total;
    @ManyToMany
    private List<Producto> listaProductos;
   @OneToOne
   @NotNull
   private Cliente unCliente;


    public Venta() {
    }

    public Venta(List<Producto> listaProductos , Cliente unCliente) {
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }

    public Long getCodigo_venta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Long codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Cliente getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }


}
