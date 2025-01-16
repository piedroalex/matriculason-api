package br.com.palm.matriculason.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.palm.matriculason.entities.Alunos;

public interface AlunosRepository extends JpaRepository<Alunos, Long> {

    @Transactional(readOnly = true)
    Page<Alunos> findByNomeContainingOrderByNome(String nome, Pageable pageable);

}
