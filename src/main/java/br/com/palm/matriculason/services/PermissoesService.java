package br.com.palm.matriculason.services;

import br.com.palm.matriculason.dtos.PermissoesDTO;
import br.com.palm.matriculason.entities.Permissoes;
import br.com.palm.matriculason.exceptions.ResourceNotFoundException;
import br.com.palm.matriculason.filters.PermissoesFilter;
import br.com.palm.matriculason.repositories.PermissoesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Service para Permissão
 *
 * @author Gisele Santos
 */
@Service
public class PermissoesService {

    @Autowired
    private PermissoesRepository permissaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    public PermissoesDTO salvar(PermissoesDTO permissoesDTO) {
        return modelMapper.map(
                permissaoRepository.save(modelMapper.map(permissoesDTO, Permissoes.class)), PermissoesDTO.class);
    }

    public void remover(Long id) {
        this.permissaoRepository.deleteById(id);
    }

    public PermissoesDTO buscarPeloIdOrFail(Long id) {
        return this.permissaoRepository.findById(id).map(p -> modelMapper.map(p, PermissoesDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(messageSource
                        .getMessage("modelo.naoEncontrado", new String[] {"Permissoes", id.toString()}, Locale.getDefault())));
    }

    public Page<PermissoesDTO> buscar(PermissoesFilter permissoesFilter, Pageable pageable){
        return this.permissaoRepository.findByDescricaoContainingOrderByDescricao(permissoesFilter.getDescricao(), pageable).map(p -> modelMapper.map(p, PermissoesDTO.class));
    }
}