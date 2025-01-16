package br.com.palm.matriculason.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.palm.matriculason.entities.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>, JpaSpecificationExecutor<Usuarios> {
		
}