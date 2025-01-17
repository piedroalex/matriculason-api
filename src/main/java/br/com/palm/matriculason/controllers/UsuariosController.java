package br.com.palm.matriculason.controllers;

import br.com.palm.matriculason.dtos.UsuariosDTO;
import br.com.palm.matriculason.filters.UsuariosFilter;
import br.com.palm.matriculason.services.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Page<UsuariosDTO>> pesquisar(UsuariosFilter filtro,
            @RequestParam(value = "page", required = false, defaultValue = "0") Optional<Integer> pagina) {
        Page<UsuariosDTO> usuarios = usuariosService.buscar(filtro, PageRequest.of(pagina.orElse(0) < 1 ? 0 : pagina.get(), 10));
        return ResponseEntity.ok(usuarios);
    }

    @CrossOrigin
    @PostMapping("/alunos/novo-aluno")
    public ResponseEntity<UsuariosDTO> cadastrarAluno(@Valid @RequestBody UsuariosDTO usuariosDTO) {
        UsuariosDTO usuarioSalvo = usuariosService.cadastrarAluno(usuariosDTO);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @CrossOrigin
    @PostMapping("/administradores/novo-administrador")
    public ResponseEntity<UsuariosDTO> cadastrarAdministrador(@Valid @RequestBody UsuariosDTO usuariosDTO) {
        UsuariosDTO usuarioSalvo = usuariosService.cadastrarAdministrador(usuariosDTO);
        return ResponseEntity.ok(usuarioSalvo);
    }
    
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<UsuariosDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody UsuariosDTO usuariosDTO) {
        usuariosDTO.setId(id);
        UsuariosDTO usuariosSalvo = usuariosService.salvarAtualizacao(usuariosDTO);
        return ResponseEntity.ok(usuariosSalvo);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        usuariosService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<UsuariosDTO> buscarPorId(@PathVariable("id") Long id) {
        UsuariosDTO usuario = usuariosService.buscarPeloIdOrFail(id);
        return ResponseEntity.ok(usuario);
    }
}
