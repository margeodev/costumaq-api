package com.costumaqos.api.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.costumaqos.api.model.Equipamento;
import com.costumaqos.api.repository.equipamento.EquipamentoRepository;
import com.costumaqos.api.repository.filter.EquipamentoFilter;
import com.costumaqos.api.repository.projection.ResumoEquipamento;
import com.costumaqos.api.service.EquipamentoService;


@RestController
@RequestMapping("/equipamentos")
public class EquipamentoResource {
	
	@Autowired
	private EquipamentoRepository equipamentos;
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	
//	################## GET METHOD ##################
	@GetMapping
	public Page<Equipamento> listar(EquipamentoFilter equipamentoFilter, Pageable pageable) {
		return equipamentos.filtrar(equipamentoFilter, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Equipamento> buscarPeloCodigo(@PathVariable Long id) {
		Equipamento equipamento = equipamentos.findOne(id);
		return equipamento != null ? ResponseEntity.ok(equipamento) : ResponseEntity.notFound().build();
	}
		
	@GetMapping(params = "resumo")
	public List<ResumoEquipamento> resumir() {
		return equipamentos.resumir();
	}

	
//	################## POST METHOD ##################
	@PostMapping
	public ResponseEntity<Equipamento> cadastrar(@RequestBody @Valid Equipamento equipamento) {
		Equipamento equipamentoSalvo = equipamentos.save(equipamento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
		path("/{id}").buildAndExpand(equipamentoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(equipamentoSalvo);
	}
	
	
//	################## DELETE METHOD ##################
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		equipamentos.delete(id);
	}
	
	
//	################## PUT METHOD ##################
	@PutMapping("/{id}")
	public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @Valid @RequestBody Equipamento equipamento) {
		Equipamento equipamentoSalvo = equipamentoService.atualizar(id, equipamento);		
		return ResponseEntity.ok(equipamentoSalvo);
	}

}
