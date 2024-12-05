package br.com.palm.matriculason.services;

import br.com.palm.matriculason.dtos.AlunosDTO;
import br.com.palm.matriculason.entities.Alunos;
import br.com.palm.matriculason.exceptions.ResourceNotFoundException;
import br.com.palm.matriculason.filters.AlunosFilter;
import br.com.palm.matriculason.repositories.AlunosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AlunosService {

    @Autowired
    private AlunosRepository alunosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    public AlunosDTO salvar(AlunosDTO alunoDTO) {
        return modelMapper.map(alunosRepository.save(modelMapper.map(alunoDTO, Alunos.class)), AlunosDTO.class);
    }

    public void remover(Long id){
        this.alunosRepository.deleteById(id);
    }

    public AlunosDTO buscarPorIdOrFail(Long id){
        return this.alunosRepository.findById(id).map(aluno -> modelMapper.map(aluno, AlunosDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(messageSource
                        .getMessage("modelo.naoEncontrado", new String[] {"Alunos", id.toString()}, Locale.getDefault())));
    }

    public Page<AlunosDTO> buscar(AlunosFilter filter, Pageable pageable) {
        return this.alunosRepository.findByNomeContainingOrderByNome(filter.getNome(), pageable).map(aluno -> modelMapper.map(aluno, AlunosDTO.class));
    }
}
