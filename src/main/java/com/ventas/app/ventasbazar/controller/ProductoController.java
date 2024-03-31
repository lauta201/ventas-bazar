package com.ventas.app.ventasbazar.controller;

import com.ventas.app.ventasbazar.model.Producto;
import com.ventas.app.ventasbazar.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/productos")
public class ProductoController {

    @Autowired
    private IProductoService service;

    @GetMapping
    public List<Producto> listaProducto(){
        return service.listaProductos();
    }

    @GetMapping("/{codigoProducto}")
    public ResponseEntity buscarProducto(@PathVariable Long codigoProducto){
        Optional<Producto> productoBd = service.buscarCodigo(codigoProducto);
        if (productoBd.isPresent()){
            return ResponseEntity.ok(productoBd);
        }
        return ResponseEntity.notFound().build();
    }



    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.ingresarProducto(producto));
    }

    @PutMapping("/editar/{codigoProducto}")
    public ResponseEntity<?> editarProducto(@PathVariable Long codigoProducto, @Valid @RequestBody Producto producto, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Producto> productoBd = service.editarProducto(codigoProducto,producto);
        if (productoBd.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(productoBd);
        }


        return ResponseEntity.notFound().build();
    }

    @GetMapping("/falta_stock")
    public List<Producto> faltaStock(){
        return service.listaStock();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "el campo: " +"'"+ err.getField()+"'"+ " "+ err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
