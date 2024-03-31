package com.ventas.app.ventasbazar.controller;

import com.ventas.app.ventasbazar.model.Cliente;
import com.ventas.app.ventasbazar.service.IClienteService;
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
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    private IClienteService service;

    @GetMapping
    public List<Cliente> listaDeClientes(){
        return service.listaCliente();
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<?> encontrarCliente(@PathVariable Long idCliente){
        Optional<Cliente> clienteOptional = service.buscarCliente(idCliente);
        if (clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{idCliente}")
    public ResponseEntity<?> clienteEliminar(@PathVariable Long idCliente){
        Optional<Cliente> clienteOptional = service.eliminarCliente(idCliente);

        if (clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/crear")
    public ResponseEntity nuevoCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.ok(service.ingresarCliente(cliente));
    }



    @PutMapping("/editar/{idCliente}")
    public ResponseEntity<?> modificarCliente(@PathVariable Long idCliente, @Valid @RequestBody Cliente cliente, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Cliente> clienteEditar = service.editarCliente(idCliente,cliente);
        if (clienteEditar.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteEditar.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "el campo: " +"'"+ err.getField()+"'"+ " "+ err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
