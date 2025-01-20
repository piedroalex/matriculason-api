package br.com.palm.matriculason.controllers;

import br.com.palm.matriculason.dtos.UsuarioResponseDTO;
import br.com.palm.matriculason.dtos.UsuariosRequestDTO;
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
    public ResponseEntity<Page<UsuariosRequestDTO>> pesquisar(UsuariosFilter filtro,
                                                              @RequestParam(value = "page", required = false, defaultValue = "0") Optional<Integer> pagina) {
        Page<UsuariosRequestDTO> usuarios = usuariosService.buscar(filtro, PageRequest.of(pagina.orElse(0) < 1 ? 0 : pagina.get(), 10));
        return ResponseEntity.ok(usuarios);
    }

    @CrossOrigin
    @PostMapping("/alunos/novo-aluno")
    public ResponseEntity<UsuarioResponseDTO> cadastrarAluno(@Valid @RequestBody UsuariosRequestDTO usuariosDTO) {
        UsuariosRequestDTO usuarioSalvo = usuariosService.cadastrarAluno(usuariosDTO);
        UsuarioResponseDTO usuarioResponseDTO = usuariosService.responseUserDTO(usuarioSalvo);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @CrossOrigin
    @PostMapping("/administradores/novo-administrador")
    public ResponseEntity<UsuarioResponseDTO> cadastrarAdministrador(@Valid @RequestBody UsuariosRequestDTO usuariosDTO) {
        UsuariosRequestDTO usuarioSalvo = usuariosService.cadastrarAdministrador(usuariosDTO);
        UsuarioResponseDTO usuarioResponseDTO = usuariosService.responseUserDTO(usuarioSalvo);
        return ResponseEntity.ok(usuarioResponseDTO);
    }
    
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<UsuariosRequestDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody UsuariosRequestDTO usuariosDTO) {
        usuariosDTO.setId(id);
        UsuariosRequestDTO usuariosSalvo = usuariosService.salvarAtualizacao(usuariosDTO);
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
    public ResponseEntity<UsuariosRequestDTO> buscarPorId(@PathVariable("id") Long id) {
        UsuariosRequestDTO usuario = usuariosService.buscarPeloIdOrFail(id);
        return ResponseEntity.ok(usuario);
    }
}
