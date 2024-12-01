package br.com.palm.matriculason.services;

import br.com.palm.matriculason.dtos.UsuarioDTO;
import br.com.palm.matriculason.entities.Usuarios;
import br.com.palm.matriculason.exceptions.ResourceNotFoundException;
import br.com.palm.matriculason.filters.UsuarioFilter;
import br.com.palm.matriculason.repositories.UsuariosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UsuariosService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public UsuarioDTO salvar(UsuarioDTO usuarioDto) {
        return modelMapper.map(usuariosRepository.save(modelMapper.map(usuarioDto, Usuarios.class)), UsuarioDTO.class);
    }

    public void remover(Long id) {
        this.usuariosRepository.deleteById(id);
    }

    public UsuarioDTO buscarPeloIdOrFail(Long id) {
        return this.usuariosRepository.findById(id).map(u -> modelMapper.map(u, UsuarioDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(messageSource
                        .getMessage("modelo.naoEncontrado", new String[] { "Usuários", id.toString() }, Locale.getDefault())));
    }

    public Page<UsuarioDTO> buscar(UsuarioFilter usuariosFilter, Pageable pageable) {
        return this.usuariosRepository.findByUsernameContainingOrderByUsername(usuariosFilter.getUsername(), pageable)
                .map(u -> modelMapper.map(u, UsuarioDTO.class));
    }

    public Page<UsuarioDTO> buscarPorStatus(Boolean status, Pageable pageable) {
        return this.usuariosRepository.findByStatus(status, pageable).map(u -> modelMapper.map(u, UsuarioDTO.class));
    }
}