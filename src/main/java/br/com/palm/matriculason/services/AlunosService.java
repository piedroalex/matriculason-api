package br.com.palm.matriculason.services;

import java.util.Locale;

import br.com.palm.matriculason.dtos.UsuarioResponseDTO;
import br.com.palm.matriculason.filters.UsuariosFilter;
import br.com.palm.matriculason.services.specifications.AlunosSpecification;
import br.com.palm.matriculason.services.specifications.UsuariosSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.palm.matriculason.dtos.AlunosDTO;
import br.com.palm.matriculason.entities.Alunos;
import br.com.palm.matriculason.entities.Cursos;
import br.com.palm.matriculason.exceptions.ResourceNotFoundException;
import br.com.palm.matriculason.filters.AlunosFilter;
import br.com.palm.matriculason.repositories.AlunosRepository;
import br.com.palm.matriculason.repositories.CursosRepository;

@Service
public class AlunosService {

    @Autowired
    private AlunosRepository alunosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private CursosRepository cursosRepository;

    public AlunosDTO salvar(AlunosDTO alunoDTO) {
    	Cursos curso = cursosRepository.findById(alunoDTO.getCurso().getId()).get();
    	Alunos aluno = modelMapper.map(alunoDTO, Alunos.class);
    	aluno.setCurso(curso);
        return modelMapper.map(alunosRepository.save(aluno), AlunosDTO.class);
    }

    public void remover(Long id){
        this.alunosRepository.deleteById(id);
    }

    public AlunosDTO buscarPorIdOrFail(Long id){
        return this.alunosRepository.findById(id).map(aluno -> modelMapper.map(aluno, AlunosDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(messageSource
                        .getMessage("modelo.naoEncontrado", new String[] {"Alunos", id.toString()}, Locale.getDefault())));
    }

    public Page<AlunosDTO> buscar(AlunosFilter alunosFilter, Pageable pageable) {
        String CPF = alunosFilter.getCpf().replaceAll("[^0-9]", "");
        alunosFilter.setCpf(CPF);
        return alunosRepository.findAll(AlunosSpecification.filtrar(alunosFilter),pageable)
                .map(u -> modelMapper.map(u, AlunosDTO .class));
    }
}
