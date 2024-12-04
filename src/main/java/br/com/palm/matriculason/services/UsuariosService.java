package br.com.palm.matriculason.services;

import br.com.palm.matriculason.dtos.UsuariosDTO;
import br.com.palm.matriculason.entities.Usuarios;
import br.com.palm.matriculason.exceptions.ResourceNotFoundException;
import br.com.palm.matriculason.filters.UsuariosFilter;
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

    public UsuariosDTO salvar(UsuariosDTO usuarioDto) {
        return modelMapper.map(usuariosRepository.save(modelMapper.map(usuarioDto, Usuarios.class)), UsuariosDTO.class);
    }

    public void remover(Long id) {
        this.usuariosRepository.deleteById(id);
    }

    public UsuariosDTO buscarPeloIdOrFail(Long id) {
        return this.usuariosRepository.findById(id).map(u -> modelMapper.map(u, UsuariosDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(messageSource
                        .getMessage("modelo.naoEncontrado", new String[] { "Usuários", id.toString() }, Locale.getDefault())));
    }

    public Page<UsuariosDTO> buscar(UsuariosFilter usuariosFilter, Pageable pageable) {
        return this.usuariosRepository.findByUsernameContainingOrderByUsername(usuariosFilter.getUsername(), pageable)
                .map(u -> modelMapper.map(u, UsuariosDTO.class));
    }

    public Page<UsuariosDTO> buscarPorStatus(Boolean status, Pageable pageable) {
        return this.usuariosRepository.findByStatus(status, pageable).map(u -> modelMapper.map(u, UsuariosDTO.class));
    }

    public Page<UsuariosDTO> buscarPorNomePessoa(String nome, Pageable pageable) {
        return this.usuariosRepository.findByPessoaNomeContainingIgnoreCase(nome, pageable).map(u -> modelMapper.map(u, UsuariosDTO.class));
    }
}