package br.com.palm.matriculason.services;

import br.com.palm.matriculason.dtos.AlunoDTO;
import br.com.palm.matriculason.entities.Aluno;
import br.com.palm.matriculason.exceptions.ResourceNotFoundException;
import br.com.palm.matriculason.filters.AlunoFilter;
import br.com.palm.matriculason.repositories.AlunosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AlunoService {

    @Autowired
    private AlunosRepository alunosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    public AlunoDTO salvar(AlunoDTO alunoDTO) {
        return modelMapper.map(alunosRepository.save(modelMapper.map(alunoDTO, Aluno.class)), AlunoDTO.class);
    }

    public void remover(Long id){
        this.alunosRepository.deleteById(id);
    }

    public AlunoDTO buscarPorIdOrFail(Long id){
        return this.alunosRepository.findById(id).map(aluno -> modelMapper.map(aluno, AlunoDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(messageSource
                        .getMessage("modelo.naoEncontrado", new String[] {"Alunos", id.toString()}, Locale.getDefault())));
    }

    public Page<AlunoDTO> buscarPorNome(AlunoFilter filter, Pageable pageable) {
        return this.alunosRepository.findByNomeContainingOrderByNome(filter.getNome(), pageable).map(aluno -> modelMapper.map(aluno, AlunoDTO.class));
    }

    public Page<AlunoDTO> buscarPorCpf(AlunoFilter filter, Pageable pageable) {
        return this.alunosRepository.findByCpfContainingOrderByCpf(filter.getCpf(), pageable).map(aluno -> modelMapper.map(aluno, AlunoDTO.class));
    }
}
