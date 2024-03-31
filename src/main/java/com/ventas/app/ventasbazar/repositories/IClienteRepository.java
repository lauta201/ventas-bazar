package com.ventas.app.ventasbazar.repositories;

import com.ventas.app.ventasbazar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
