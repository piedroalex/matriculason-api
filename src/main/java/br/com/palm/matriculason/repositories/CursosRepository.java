package br.com.palm.matriculason.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.palm.matriculason.entities.Cursos;

/**
 * Repository para Cursos
 *
 * @author Pedro Alex
 */
@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long> {
	
	@Transactional(readOnly = true)
    Page<Cursos> findByNomeContainingOrderByNome(String nome, Pageable pageable);

    Cursos findAllById(Long id);
}
