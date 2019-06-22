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

import com.costumaqos.api.model.Servico;
import com.costumaqos.api.repository.servico.ServicoRepository;
import com.costumaqos.api.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoResource {
	
	@Autowired
	private ServicoRepository servicos;
	
	@Autowired
	private ServicoService servicoService;
	
//	################## GET METHOD ##################
	@GetMapping
	public List<Servico> listar(String numeroSerie) {
		return servicos.findByOrdemServicoEquipamentoNumeroSerie(numeroSerie);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Servico> buscarPeloCodigo(@PathVariable Long id) {
		Servico servico = servicos.findOne(id);
		return servico != null ? ResponseEntity.ok(servico) : ResponseEntity.notFound().build();
	}
	
	@GetMapping(params = "naoConcluido")
	public List<Servico> findByNaoConcluido() {
		return servicos.findByConcluido(false);
	}
	
	@GetMapping(params = "concluido")
	public Page<Servico> findByConcluido(Pageable pageable) {
		return servicos.findByConcluidoOrderByIdDesc(true, pageable);
	}
		
	
//	################## POST METHOD ##################
	@PostMapping
	public ResponseEntity<Servico> cadastrar(@RequestBody @Valid Servico servico) {
		Servico servicoSalvo = servicos.save(servico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
		path("/{id}").buildAndExpand(servicoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(servicoSalvo);
	}
	
	
//	################## DELETE METHOD ##################
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		servicos.delete(id);
	}
	
	
//	################## PUT METHOD ##################
	@PutMapping("/{id}")
	public ResponseEntity<Servico> atualizar(@PathVariable Long id, @Valid @RequestBody Servico servico) {
		Servico servicoSalvo = servicoService.atualizar(id, servico);		
		return ResponseEntity.ok(servicoSalvo);
	}
}
