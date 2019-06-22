package com.costumaqos.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.costumaqos.api.model.Cliente;
import com.costumaqos.api.repository.cliente.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clientes;
	
	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteSalvo = buscarClientePeloId(id);
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return clientes.save(clienteSalvo);
	}
	
	public Cliente buscarClientePeloId(Long id) {
		Cliente clienteSalvo = clientes.findOne(id);
		if (clienteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return clienteSalvo;
	}
}
