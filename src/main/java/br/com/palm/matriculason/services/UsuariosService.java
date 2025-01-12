package br.com.palm.matriculason.services;

import br.com.palm.matriculason.dtos.UsuariosDTO;
import br.com.palm.matriculason.entities.Alunos;
import br.com.palm.matriculason.entities.Usuarios;
import br.com.palm.matriculason.exceptions.ResourceNotFoundException;
import br.com.palm.matriculason.filters.UsuariosFilter;
import br.com.palm.matriculason.repositories.UsuariosRepository;
import br.com.palm.matriculason.services.specifications.UsuariosSpecification;
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

    public UsuariosDTO salvarAtualizacao(UsuariosDTO usuarioDto) {
        Usuarios usuarios = modelMapper.map(usuarioDto, Usuarios.class);

        if (usuarios.getUsername() == null || usuarios.getUsername().isBlank()) {
            usuarios.setUsername(usuarioDto.getPessoa().getCpf());
        }

        usuarios.setSenha(usuarioDto.getSenha());

        return modelMapper.map(usuariosRepository.save(modelMapper.map(usuarios, Usuarios.class)), UsuariosDTO.class);
    }

    public UsuariosDTO cadastrarAluno(UsuariosDTO usuarioDto) {
        validarSenhasIguais(usuarioDto);
        Usuarios usuario = modelMapper.map(usuarioDto, Usuarios.class);
        usuario.setUsername(usuarioDto.getPessoa().getCpf());
        usuario.setStatus(true);
        return modelMapper.map(usuariosRepository.save(usuario), UsuariosDTO.class);
    }


    public UsuariosDTO cadastrarAdministrador(UsuariosDTO usuarioDto) {
        validarSenhasIguais(usuarioDto);
        Usuarios usuario = modelMapper.map(usuarioDto, Usuarios.class);
        usuario.setUsername(usuarioDto.getPessoa().getCpf());
        usuario.setStatus(true);
        return modelMapper.map(usuariosRepository.save(usuario), UsuariosDTO.class);
    }

    public void validarSenhasIguais(UsuariosDTO usuarioDto) {
        if (usuarioDto.getConfirmarSenha() != null && !usuarioDto.getConfirmarSenha().equals(usuarioDto.getSenha())) {
            throw new IllegalArgumentException("As senhas não coincidem.");
        }
    }


    public void remover(Long id) {
        this.usuariosRepository.deleteById(id);
    }

    public UsuariosDTO buscarPeloIdOrFail(Long id) {
        return this.usuariosRepository.findById(id)
                .map(u -> modelMapper.map(u, UsuariosDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage(
                                "modelo.naoEncontrado",
                                new String[]{"Usuários", id.toString()},
                                Locale.getDefault()
                        )
                ));
    }

    public Page<UsuariosDTO> buscar(UsuariosFilter usuariosFilter, Pageable pageable) {
        return usuariosRepository.findAll(UsuariosSpecification.filtrar(usuariosFilter), pageable)
                .map(u -> modelMapper.map(u, UsuariosDTO.class));
    }
}
