package br.com.palm.matriculason.services;

import java.util.Locale;
import java.util.Optional;

import br.com.palm.matriculason.dtos.UsuarioResponseDTO;
import br.com.palm.matriculason.entities.Cursos;
import br.com.palm.matriculason.repositories.CursosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.palm.matriculason.dtos.UsuariosRequestDTO;
import br.com.palm.matriculason.entities.Administradores;
import br.com.palm.matriculason.entities.Alunos;
import br.com.palm.matriculason.entities.Usuarios;
import br.com.palm.matriculason.exceptions.ResourceNotFoundException;
import br.com.palm.matriculason.filters.UsuariosFilter;
import br.com.palm.matriculason.repositories.UsuariosRepository;
import br.com.palm.matriculason.services.specifications.UsuariosSpecification;


@Service
public class UsuariosService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private CursosRepository cursosRepository;

    public UsuariosRequestDTO salvarAtualizacao(UsuariosRequestDTO usuariosDTO) {
        String CPF = usuariosDTO.getPessoa().getCpf().replaceAll("[^0-9]", "");
        usuariosDTO.getPessoa().setCpf(CPF);

        Optional<Usuarios> usuarioExistenteOpt = usuariosRepository.findById(usuariosDTO.getId());
        if (usuarioExistenteOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Usuarios usuarioExistente = usuarioExistenteOpt.get();

        if (usuariosDTO.getPessoa() instanceof Alunos) {
            Alunos alunos = (Alunos) usuariosDTO.getPessoa();
            Alunos alunoExistente = (Alunos) usuarioExistente.getPessoa();
            if (!alunoExistente.getCpf().equals(alunos.getCpf())) {
                throw new RuntimeException("CPF do aluno não pode ser alterado");
            }
            if (alunos.getMatricula() != null && !alunos.getMatricula().equals(alunoExistente.getMatricula())) {
                alunoExistente.setMatricula(alunos.getMatricula());
            }
            if (alunos.getNome() != null) {
                alunoExistente.setNome(alunos.getNome());
            }
            if (alunos.getEmail() != null) {
                alunoExistente.setEmail(alunos.getEmail());
            }
            usuarioExistente.setPessoa(alunoExistente);
        } else if (usuariosDTO.getPessoa() instanceof Administradores) {
            Administradores administradores = (Administradores) usuariosDTO.getPessoa();
            Administradores administradoresExistente = (Administradores) usuarioExistente.getPessoa();
            if(!administradoresExistente.getCpf().equals(administradores.getCpf())) {
                throw new RuntimeException("CPF do administrador não pode ser alterado");
            }
            if (administradores.getNome() != null) {
                administradoresExistente.setNome(administradores.getNome());
            }
            if (administradores.getEmail() != null) {
                administradoresExistente.setEmail(administradores.getEmail());
            }
            if (administradores.getCargo() != null) {
                administradoresExistente.setCargo(administradores.getCargo());
            }
            if (administradores.getDepartamento() != null) {
                administradoresExistente.setDepartamento(administradores.getDepartamento());
            }
            usuarioExistente.setPessoa(administradoresExistente);
        }
        if (usuariosDTO.getSenha() != null) {
            usuarioExistente.setSenha(usuariosDTO.getSenha());
        }
        usuarioExistente = usuariosRepository.save(usuarioExistente);
        return modelMapper.map(usuarioExistente, UsuariosRequestDTO.class);
    }

    public UsuariosRequestDTO cadastrarAluno(UsuariosRequestDTO usuarioDto) {
        Usuarios usuario = modelMapper.map(usuarioDto, Usuarios.class);
        Alunos alunos = (Alunos) usuarioDto.getPessoa();
        Cursos cursos = cursosRepository.findAllById(alunos.getCurso().getId());

        String CPF = usuarioDto.getPessoa().getCpf().replaceAll("[^0-9]", "");
        alunos.setCpf(CPF);
        alunos.setCurso(cursos);
        usuario.setUsername(CPF);
        usuario.setStatus(true);
        usuario.setPessoa(alunos);

        return modelMapper.map(usuariosRepository.save(usuario), UsuariosRequestDTO.class);
    }

    public UsuariosRequestDTO cadastrarAdministrador(UsuariosRequestDTO usuarioDto) {
        Usuarios usuario = modelMapper.map(usuarioDto, Usuarios.class);
        String CPF = usuarioDto.getPessoa().getCpf().replaceAll("[^0-9]", "");

        usuario.setUsername(CPF);
        usuario.setStatus(true);

        usuariosRepository.save(usuario);

        return modelMapper.map(  usuariosRepository.save(usuario), UsuariosRequestDTO.class);
    }

    public UsuarioResponseDTO responseUserDTO(UsuariosRequestDTO usuariosRequestDTO){
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(usuariosRequestDTO.getId());
        usuarioResponseDTO.setUsername(usuariosRequestDTO.getPessoa().getCpf());
        usuarioResponseDTO.setPessoa(usuariosRequestDTO.getPessoa());
        usuarioResponseDTO.setStatus(true);
        usuarioResponseDTO.setUsername(usuariosRequestDTO.getPessoa().getCpf());

        return usuarioResponseDTO;
    }

    public void remover(Long id) {
        this.usuariosRepository.deleteById(id);
    }

    public UsuariosRequestDTO buscarPeloIdOrFail(Long id) {
        return this.usuariosRepository.findById(id)
                .map(u -> modelMapper.map(u, UsuariosRequestDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage(
                                "modelo.naoEncontrado",
                                new String[]{"Usuários", id.toString()},
                                Locale.getDefault()
                        )
                ));
    }

    public Page<UsuarioResponseDTO> buscar(UsuariosFilter usuariosFilter, Pageable pageable) {
        return usuariosRepository.findAll(UsuariosSpecification.filtrar(usuariosFilter), pageable)
                .map(u -> modelMapper.map(u, UsuarioResponseDTO.class));
    }
}
