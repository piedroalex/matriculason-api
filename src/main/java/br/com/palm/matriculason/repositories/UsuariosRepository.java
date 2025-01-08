package br.com.palm.matriculason.repositories;

import br.com.palm.matriculason.entities.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>, JpaSpecificationExecutor<Usuarios> {

    Page<Usuarios> findByUsernameContainingOrderByUsername(String username, Pageable pageable);

    Usuarios findByUsername(String username);

    Page<Usuarios> findByStatus(Boolean status, Pageable pageable);

    Page<Usuarios> findByPessoaNomeContainingIgnoreCase(String nome, Pageable pageable);
}
