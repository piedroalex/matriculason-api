package br.com.palm.matriculason.repositories;

import br.com.palm.matriculason.entities.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    @Transactional(readOnly = true)
    Page<Usuarios> findByUsernameContainingOrderByUsername(String username, Pageable pageable);

    @Transactional(readOnly = true)
    Usuarios findByUsername(String username);

    @Transactional(readOnly = true)
    Page<Usuarios> findByStatus(Boolean status, Pageable pageable);
}