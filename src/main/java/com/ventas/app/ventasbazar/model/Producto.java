package com.ventas.app.ventasbazar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private Long codigoProducto;
    @NotBlank (message = "{NotBlank.producto.nombre}")
    private String nombre;

    @NotBlank(message = "{NotBlank.producto.marca}")
    private String marca;

    @NotNull
    private Double costo;

    @Column(name = "cantidad_disponible")
    @NotNull
    @Min( message = "{Min.producto.cantidadDisponible}", value = 0 )
    private Double cantidadDisponible;

    public Producto() {
    }

    public Producto( String nombre, String marca, Double costo, Double cantidadDisponible) {

        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidadDisponible = cantidadDisponible;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
