package br.com.palm.matriculason.controllers;

import br.com.palm.matriculason.dtos.PermissoesDTO;
import br.com.palm.matriculason.filters.PermissoesFilter;
import br.com.palm.matriculason.services.PermissoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController @RequestMapping("permissoes")
public class PermissoesController {

	@Autowired
	private PermissoesService permissaoService;

	@PostMapping
	public ResponseEntity<PermissoesDTO> cadastrar(@Valid @RequestBody PermissoesDTO permissoesDTO) {
		return ResponseEntity.ok(this.permissaoService.salvar(permissoesDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PermissoesDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody PermissoesDTO permissoesDTO) {
		permissoesDTO.setId(id);
		return ResponseEntity.ok(this.permissaoService.salvar(permissoesDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		this.permissaoService.remover(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PermissoesDTO> buscarPorId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.permissaoService.buscarPeloIdOrFail(id));
	}

	@GetMapping
	public ResponseEntity<Page<PermissoesDTO>> pesquisar(@ModelAttribute("filtro") PermissoesFilter filtro,
			@RequestParam(value = "page", required = false, defaultValue = "0") Optional<Integer> pagina) {
		return ResponseEntity.ok().body(
				permissaoService.buscar(filtro, PageRequest.of((pagina.orElse(0) < 1) ? 0 : pagina.get(), 10)));
	}
}
