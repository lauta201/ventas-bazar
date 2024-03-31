package com.ventas.app.ventasbazar.service;

import com.ventas.app.ventasbazar.model.Cliente;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IClienteService {
    List<Cliente> listaCliente();

    Cliente ingresarCliente(Cliente cliente);

    Optional<Cliente> eliminarCliente(Long idCliente);

    Optional<Cliente> buscarCliente(Long idCliente);

    Optional<Cliente> editarCliente(Long idCliente, Cliente cliente);
}
