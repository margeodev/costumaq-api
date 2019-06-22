package com.costumaqos.api.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.costumaqos.api.model.Mecanico;
import com.costumaqos.api.repository.MecanicoRepository;

@RestController
@RequestMapping("/mecanicos")
public class MecanicoResource {
	
	@Autowired
	private MecanicoRepository mecanicos;

//	################## GET METHOD ##################
	@GetMapping
	public List<Mecanico> listar() {
		return mecanicos.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mecanico> buscarPeloCodigo(@PathVariable Long id) {
		Mecanico mecanico = mecanicos.findOne(id);
		return mecanico != null ? ResponseEntity.ok(mecanico) : ResponseEntity.notFound().build();
	}
	
//	################## POST METHOD ##################
	@PostMapping
	public ResponseEntity<Mecanico> cadastrar(@RequestBody @Valid Mecanico mecanico) {
		Mecanico mecanicoSalvo = mecanicos.save(mecanico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
		path("/{id}").buildAndExpand(mecanicoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(mecanicoSalvo);
	}
}
