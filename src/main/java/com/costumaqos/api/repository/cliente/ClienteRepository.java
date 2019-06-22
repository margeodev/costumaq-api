package com.costumaqos.api.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import com.costumaqos.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery {

}
