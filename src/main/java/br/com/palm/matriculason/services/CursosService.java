package br.com.palm.matriculason.services;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.palm.matriculason.dtos.CursosDTO;
import br.com.palm.matriculason.entities.Cursos;
import br.com.palm.matriculason.exceptions.ResourceNotFoundException;
import br.com.palm.matriculason.filters.CursosFilter;
import br.com.palm.matriculason.repositories.CursosRepository;

/**
 * Service para Cursos
 *
 * @author Pedro Alex
 */
@Service
public class CursosService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CursosRepository cursoRepository;

	public CursosDTO salvar(CursosDTO cursoDto) {
		return modelMapper.map(cursoRepository.save(modelMapper.map(cursoDto, Cursos.class)), CursosDTO.class);
	}

	public void remover(Long id) {
		this.cursoRepository.deleteById(id);
	}

	public CursosDTO buscarPeloIdOrFail(Long id) {
		return this.cursoRepository.findById(id).map(c -> modelMapper.map(c, CursosDTO.class))
				.orElseThrow(() -> new ResourceNotFoundException(messageSource
				.getMessage("modelo.naoEncontrado", new String[] { "Cursos", id.toString() }, Locale.getDefault())));
	}

	public Page<CursosDTO> buscar(CursosFilter cursoFilter, Pageable pageable) {
		return this.cursoRepository.findByNomeContainingOrderByNome(cursoFilter.getNome(), pageable).map(c -> modelMapper.map(c, CursosDTO.class));
	}
	
}