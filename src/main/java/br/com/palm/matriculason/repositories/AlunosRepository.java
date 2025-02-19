package br.com.palm.matriculason.repositories;

import br.com.palm.matriculason.entities.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.palm.matriculason.entities.Alunos;

@Repository
public interface AlunosRepository extends JpaRepository<Alunos, Long>, JpaSpecificationExecutor<Alunos> {

}
