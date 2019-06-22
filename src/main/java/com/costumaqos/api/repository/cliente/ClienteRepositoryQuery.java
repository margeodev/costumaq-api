package com.costumaqos.api.repository.cliente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.costumaqos.api.model.Cliente;
import com.costumaqos.api.repository.filter.ClienteFilter;
import com.costumaqos.api.repository.projection.ResumoCliente;
import com.costumaqos.api.repository.projection.ResumoClienteMax;

public interface ClienteRepositoryQuery {
	
	public Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable);
	public Page<ResumoCliente> resumir(ClienteFilter filter, Pageable pageable);
	public List<ResumoClienteMax> resumir();
	
}
