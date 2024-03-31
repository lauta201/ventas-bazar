package com.ventas.app.ventasbazar.service;

import com.ventas.app.ventasbazar.model.Cliente;
import com.ventas.app.ventasbazar.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository repository;
    @Override
    public List<Cliente> listaCliente() {
        return repository.findAll();
    }

    @Override
    public Cliente ingresarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Optional<Cliente> eliminarCliente(Long idCliente) {
        Optional<Cliente> clienteDB = repository.findById(idCliente);
        clienteDB.ifPresent(prod ->{repository.delete(prod);});
        return clienteDB;
    }

    @Override
    public Optional<Cliente> buscarCliente(Long idCliente) {
        return repository.findById(idCliente);
    }

    @Override
    public Optional<Cliente> editarCliente(Long idCliente, Cliente cliente) {
        Optional<Cliente> clienteOptional = repository.findById(idCliente);
        if (clienteOptional.isPresent()){
            Cliente clienteBd= clienteOptional.orElseThrow();
            clienteBd.setDni(cliente.getDni());
            clienteBd.setApellido(cliente.getApellido());
            clienteBd.setNombre(cliente.getNombre());
            return Optional.of(repository.save(clienteBd));
        }
        return clienteOptional;
    }
}
