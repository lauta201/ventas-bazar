package com.ventas.app.ventasbazar.controller;

import com.ventas.app.ventasbazar.model.Producto;
import com.ventas.app.ventasbazar.model.Venta;
import com.ventas.app.ventasbazar.model.VentaDto;
import com.ventas.app.ventasbazar.model.VentaMayorDto;
import com.ventas.app.ventasbazar.service.IVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ventas")
public class VentaController {

    @Autowired
    private IVentaService service;

    @GetMapping
    public List<Venta> listaVenta(){
        return service.listaVenta();
    }

    @GetMapping("/{codigoVenta}")
    public ResponseEntity<?> buscarVenta(@PathVariable Long codigoVenta){

        Optional<Venta> ventaOptional = service.buscarVenta(codigoVenta);
        if (ventaOptional.isPresent()){
            return ResponseEntity.ok(ventaOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{codigoVenta}")
    public void eliminarVenta(@PathVariable Long codigoVenta){
       service.eliminarVenta(codigoVenta);

    }

    @PostMapping("/crear")
    public ResponseEntity<?> ventasCrear(@Valid @RequestBody Venta venta, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearVenta(venta));
    }

    @PutMapping("/editar/{codigoVenta}")
    public ResponseEntity<?> codigoVenta(@Valid @RequestBody Venta venta,BindingResult result,@PathVariable Long codigoVenta ){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Venta> ventaOptional = service.modificarVenta(codigoVenta, venta);
        if (ventaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(service.modificarVenta(codigoVenta,venta));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/productos/{codigoVenta}")
    public List<Producto> listaProductos(@PathVariable Long codigoVenta){
        return service.listaDeProductos(codigoVenta);
    }

    @GetMapping("/fecha/{fecha_venta}")
    public VentaDto VentaDto(@PathVariable LocalDate fecha_venta){
        return service.listaVentas(fecha_venta);
    }

    @GetMapping("/mayor")
    public VentaMayorDto mayorVenta(){
        return service.ventaMayor();
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> error = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            error.put(err.getField(),"El campo"+ "'"+err.getField()+"'"+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(error);
    }
}
