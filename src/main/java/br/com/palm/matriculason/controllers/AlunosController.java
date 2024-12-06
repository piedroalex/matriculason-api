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

import br.com.palm.matriculason.dtos.AlunosDTO;
import br.com.palm.matriculason.filters.AlunosFilter;
import br.com.palm.matriculason.services.AlunosService;
import jakarta.validation.Valid;

@RestController @RequestMapping("alunos")
public class AlunosController {

    @Autowired
    AlunosService alunosService;

    @PostMapping
    public ResponseEntity<AlunosDTO> cadastrar(@Valid @RequestBody AlunosDTO alunosDTO) {
        return ResponseEntity.ok(this.alunosService.salvar(alunosDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunosDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody AlunosDTO alunosDTO) {
        alunosDTO.setId(id);
        return ResponseEntity.ok(this.alunosService.salvar(alunosDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        this.alunosService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunosDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.alunosService.buscarPorIdOrFail(id));
    }

    @GetMapping
    public ResponseEntity<Page<AlunosDTO>> pesquisar(@ModelAttribute("filtro")AlunosFilter filtro, @RequestParam(value = "page", required = false, defaultValue = "0")Optional<Integer> pagina){
        return ResponseEntity.ok().body(
                alunosService.buscar(filtro, PageRequest.of((pagina.orElse(0) < 1) ? 0 : pagina.get(), 10))
        );
    }
}
