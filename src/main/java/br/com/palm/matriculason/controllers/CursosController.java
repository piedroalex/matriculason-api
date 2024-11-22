package br.com.palm.matriculason.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.palm.matriculason.dtos.CursosDTO;
import br.com.palm.matriculason.filters.CursosFilter;
import br.com.palm.matriculason.services.CursosService;
import jakarta.validation.Valid;

/**
 * RestController para Cursos
 *
 * @author Pedro Alex
 */
@RestController @RequestMapping("cursos")
public class CursosController {

	@Autowired
	private CursosService cursoService;
	
	@PostMapping
	public ResponseEntity<CursosDTO> cadastrar(@Valid @RequestBody CursosDTO cursoDTO) {
		return ResponseEntity.ok(this.cursoService.salvar(cursoDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CursosDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody CursosDTO cursoDTO) throws Exception {						
		cursoDTO.setId(id);
		return ResponseEntity.ok(this.cursoService.salvar(cursoDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		this.cursoService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CursosDTO> buscarPorId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.cursoService.buscarPeloIdOrFail(id));
	}

	@GetMapping
	public ResponseEntity<Page<CursosDTO>> pesquisar(@ModelAttribute("filtro") CursosFilter filtro,
			@RequestParam(value = "page", required = false, defaultValue = "0") Optional<Integer> pagina) {
		return ResponseEntity.ok().body(
				cursoService.buscar(filtro, PageRequest.of((pagina.orElse(0) < 1) ? 0 : pagina.get(), 10)));
	}
}
