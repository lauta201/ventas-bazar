package com.ventas.app.ventasbazar.service;

import com.ventas.app.ventasbazar.model.Producto;
import com.ventas.app.ventasbazar.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository repository;
    @Override
    public List<Producto> listaProductos() {
        return repository.findAll();
    }

    @Override
    public Optional<Producto> buscarCodigo(Long codigo) {
        return repository.findById(codigo);
    }

    @Override
    public Producto ingresarProducto(Producto producto) {
        return repository.save(producto);
    }



    @Override
    public Optional<Producto> editarProducto(Long codigo, Producto producto) {
        Optional<Producto> editar = repository.findById(codigo);
        if (editar.isPresent()){
            Producto editarBd = editar.orElseThrow();
            editarBd.setCosto(producto.getCosto());
            editarBd.setMarca(producto.getMarca());
            editarBd.setNombre(producto.getNombre());
            editarBd.setCantidadDisponible(producto.getCantidadDisponible());
            return Optional.of(repository.save(editarBd));
        }

        return editar;
    }

    @Override
    public List<Producto> listaStock(){
        List<Producto> listaStock= repository.findAll();
        List<Producto>pocoStock=new ArrayList<>();
        for (Producto prod : listaStock){
            if (prod.getCantidadDisponible() <= 5){
                pocoStock.add(prod);
            }
        }

        return pocoStock;
    }
}
