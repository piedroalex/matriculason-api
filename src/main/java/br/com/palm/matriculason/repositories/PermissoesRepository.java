package br.com.palm.matriculason.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.palm.matriculason.entities.Permissoes;

/**
 * Repository para Permissao
 *
 * @author Gisele Santos
 */
@Repository
public interface PermissoesRepository extends JpaRepository<Permissoes, Long> {

    @Transactional(readOnly = true)
    Page<Permissoes> findByDescricaoContainingOrderByDescricao(String descricao, Pageable pageable);

}
