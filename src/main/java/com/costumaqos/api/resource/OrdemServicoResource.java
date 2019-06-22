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

import com.costumaqos.api.model.OrdemServico;
import com.costumaqos.api.repository.ordemServico.OrdemServicoRepository;
import com.costumaqos.api.repository.projection.ResumoOrdemServico;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoResource {

	@Autowired
	private OrdemServicoRepository servicos;
	
//	################## GET METHOD ##################	
	@GetMapping("/{codigo}")
	public ResponseEntity<OrdemServico> buscarPeloCodigo(@PathVariable Long codigo) {
		OrdemServico servico = servicos.findOne(codigo);
		return servico != null ? ResponseEntity.ok(servico) : ResponseEntity.notFound().build();
	}
	
	@GetMapping(params = "resumo")
	public List<ResumoOrdemServico> resumir() {
		return servicos.resumir();
	}
	
	
//	################## POST METHOD ##################
	@PostMapping
	public ResponseEntity<OrdemServico> cadastrar(@RequestBody @Valid OrdemServico servico) {
		OrdemServico servicoSalvo = servicos.save(servico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
		path("/{codigo}").buildAndExpand(servicoSalvo.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).body(servicoSalvo);
	}
	
}
