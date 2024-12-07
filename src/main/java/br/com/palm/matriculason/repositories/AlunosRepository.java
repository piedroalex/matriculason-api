package br.com.palm.matriculason.repositories;

import br.com.palm.matriculason.entities.Alunos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AlunosRepository extends JpaRepository<Alunos, Long> {

    @Transactional(readOnly = true)
    Page<Alunos> findByNomeContainingOrderByNome(String nome, Pageable pageable);

    @Transactional(readOnly = true)
    Page<Alunos> findByCpfContainingOrderByCpf(String cpf, Pageable pageable);

    @Transactional(readOnly = true)
    Alunos findByCpf(String cpf);

    @Transactional(readOnly = true)
    Alunos findByNome(String nome);

}
