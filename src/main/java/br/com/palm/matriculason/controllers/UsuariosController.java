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

    @PostMapping
    public ResponseEntity<UsuariosDTO> cadastrar(@Valid @RequestBody UsuariosDTO usuariosDTO) {
        UsuariosDTO usuariosSalvo = usuariosService.salvar(usuariosDTO);
        return ResponseEntity.ok(usuariosSalvo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuariosDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody UsuariosDTO usuariosDTO) {
        usuariosDTO.setId(id);
        UsuariosDTO usuariosSalvo = usuariosService.salvar(usuariosDTO);
        return ResponseEntity.ok(usuariosSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        usuariosService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosDTO> buscarPorId(@PathVariable("id") Long id) {
        UsuariosDTO usuario = usuariosService.buscarPeloIdOrFail(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<UsuariosDTO>> pesquisar(
            @ModelAttribute("filtro") UsuariosFilter filtro,
            @RequestParam(value = "page", required = false, defaultValue = "0") Optional<Integer> pagina) {
        Page<UsuariosDTO> usuarios = usuariosService.buscar(
                filtro,
                PageRequest.of((pagina.orElse(0) < 1) ? 0 : pagina.get(), 10));
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/status")
    public ResponseEntity<Page<UsuariosDTO>> buscarPorStatus(
            @ModelAttribute("filtro") UsuariosFilter filtro, 
            @RequestParam(value = "page", required = false, defaultValue = "0") Optional<Integer> pagina) {

        Boolean status = filtro.getStatus();
        Page<UsuariosDTO> usuarios = usuariosService.buscarPorStatus(
                status,
                PageRequest.of((pagina.orElse(0) < 1) ? 0 : pagina.get(), 10)
        );
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/nome-pessoa")
    public ResponseEntity<Page<UsuariosDTO>> buscarPorNomePessoa(
            @ModelAttribute("filtro") UsuariosFilter filtro,
            @RequestParam(value = "page", required = false, defaultValue = "0") Optional<Integer> pagina) {

        String nome = filtro.getNome();
        Page<UsuariosDTO> usuarios = usuariosService.buscarPorNomePessoa(
                nome,
                PageRequest.of((pagina.orElse(0) < 1) ? 0 : pagina.get(), 10)
        );
        return ResponseEntity.ok(usuarios);
    }
}
