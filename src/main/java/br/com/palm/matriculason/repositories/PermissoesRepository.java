package br.com.palm.matriculason.repositories;

import br.com.palm.matriculason.entities.Permissoes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository para Permissao
 *
 * @author Gisele Santos
 */
@Repository
public interface PermissoesRepository extends JpaRepository<Permissoes, Long> {

    @Transactional(readOnly = true)
    Page<Permissoes> findByRoleAndDescricaoContainingOrderByRole(String Role, String descricao, Pageable pageable);

    @Transactional(readOnly = true)
    Permissoes findByRoleAndDescricaoContaining(String Role, String descricao);
}
