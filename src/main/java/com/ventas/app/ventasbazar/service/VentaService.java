package com.ventas.app.ventasbazar.service;

import com.ventas.app.ventasbazar.model.*;
import com.ventas.app.ventasbazar.repositories.IClienteRepository;
import com.ventas.app.ventasbazar.repositories.IProductoRepository;
import com.ventas.app.ventasbazar.repositories.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private IVentaRepository repository;

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Venta> listaVenta() {

        return repository.findAll();
    }

    @Override
    public  Optional<Venta> buscarVenta(Long codigoVenta) {

       return repository.findById(codigoVenta);

    }

    @Override
    @Transactional
    public Venta crearVenta(Venta venta) {
        List<Producto> listaProductos = new ArrayList<>();
         Double total = 00.0;

        for (Producto lista : venta.getListaProductos()){
            Producto prod = productoRepository.findById(lista.getCodigoProducto()).orElse(null);
            prod.setCantidadDisponible(prod.getCantidadDisponible()-1.00);
            prod.setCodigoProducto(prod.getCodigoProducto());
            productoRepository.save(prod);
            listaProductos.add(prod);
        }

        for (Producto valor: listaProductos){
            total+= valor.getCosto();
        }

        venta.getListaProductos();
        venta.setTotal(total);
        return repository.save(venta);
    }

    @Override
    public  Optional<Venta> eliminarVenta(Long codigoVenta) {
        Optional<Venta> eliminarOp = repository.findById(codigoVenta);
        eliminarOp.ifPresent(venta ->{repository.delete(venta);
        });
        return eliminarOp;

    }

    @Override
    public Optional<Venta> modificarVenta(Long codigoVenta, Venta venta) {
        Optional<Venta> modificarOp = repository.findById(codigoVenta);

        if (modificarOp.isPresent()){
            Venta ventaModificar = modificarOp.orElseThrow();
            ventaModificar.setFecha_venta(venta.getFecha_venta());
            ventaModificar.setTotal(ventaModificar.getTotal());
            ventaModificar.setListaProductos(venta.getListaProductos());
            ventaModificar.setUnCliente(venta.getUnCliente());
            return Optional.of(repository.save(ventaModificar));
        }
        return modificarOp;
    }

    @Override
    public List<Producto> listaDeProductos(Long codigoVenta) {
        Venta venta = repository.findById(codigoVenta).orElse(null);
        List<Producto>listaProductos = new ArrayList<>();
        for (Producto prod : venta.getListaProductos()){
            listaProductos.add(prod);
        }
        return listaProductos;
    }


    @Override
    public VentaDto listaVentas(LocalDate fecha_venta){
        VentaDto ventaDto = new VentaDto();
        Double valorTotal = 0.00;
        List<Venta> lista = repository.listaVentas(fecha_venta);
        for (Venta ven: lista){
            valorTotal+= ven.getTotal();
        }
        ventaDto.setTotal(valorTotal);
        ventaDto.setListaVentas(lista);
        return ventaDto;
    }

    @Override
    public VentaMayorDto ventaMayor(){
        List<Venta> listaVentas = repository.findAll();
        listaVentas.sort(Comparator.comparing(Venta::getTotal).reversed());
        Venta venta = listaVentas.get(0);
        VentaMayorDto ventaMayorDto = new VentaMayorDto();
        ventaMayorDto.setCodigoVenta(venta.getCodigo_venta());
        ventaMayorDto.setNombre(venta.getUnCliente().getNombre());
        ventaMayorDto.setApellido(venta.getUnCliente().getApellido());
        ventaMayorDto.setTotal(venta.getTotal());
        ventaMayorDto.setListaProductos(venta.getListaProductos());
        return ventaMayorDto;
    }

}
