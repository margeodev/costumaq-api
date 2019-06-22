package com.costumaqos.api.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.costumaqos.api.model.Cliente;
import com.costumaqos.api.repository.cliente.ClienteRepository;
import com.costumaqos.api.repository.filter.ClienteFilter;
import com.costumaqos.api.repository.projection.ResumoCliente;
import com.costumaqos.api.repository.projection.ResumoClienteMax;
import com.costumaqos.api.service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clientes;
	
	@Autowired
	private ClienteService clienteService;
	
//	################## GET METHOD ##################
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_LISTAR_TUDO')")
	public Page<Cliente> listar(ClienteFilter clienteFilter, Pageable pageable) {
		return clientes.filtrar(clienteFilter, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_LISTAR_TUDO')")
	public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable Long id) {
		Cliente cliente = clientes.findOne(id);
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_LISTAR_TUDO')")
	public Page<ResumoCliente> resumir(ClienteFilter clienteFilter, Pageable pageable) {
		return clientes.resumir(clienteFilter, pageable);
	}
	
	@GetMapping(params = "resumoMax")
	@PreAuthorize("hasAuthority('ROLE_LISTAR_TUDO')")
	public List<ResumoClienteMax> resumir() {
		return clientes.resumir();
	}

	
//	################## POST METHOD ##################
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_EDITAR_TUDO')")
	public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
		Cliente clienteSalvo = clientes.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
		path("/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(clienteSalvo);
	}
	
//	################## DELETE METHOD ##################
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_EDITAR_TUDO')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		clientes.delete(id);
	}
	
//	################## PUT METHOD ##################
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente clienteSalvo = clienteService.atualizar(id, cliente);		
		return ResponseEntity.ok(clienteSalvo);
	}
}