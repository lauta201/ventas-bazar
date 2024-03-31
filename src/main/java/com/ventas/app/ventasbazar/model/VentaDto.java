package com.ventas.app.ventasbazar.model;

import jakarta.persistence.Entity;

import java.util.List;

public class VentaDto {

private Double total;
private List<Venta>listaVentas;


    public VentaDto() {
    }

    public VentaDto(Double total, List<Venta> listaVentas) {
        this.total = total;
        this.listaVentas = listaVentas;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }
}
