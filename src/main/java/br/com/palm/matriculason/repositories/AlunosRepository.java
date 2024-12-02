package br.com.palm.matriculason.repositories;

import br.com.palm.matriculason.entities.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AlunosRepository extends JpaRepository<Aluno, Long> {

    @Transactional(readOnly = true)
    Page<Aluno> findByNomeContainingOrderByNome(String nome, Pageable pageable);

    @Transactional(readOnly = true)
    Page<Aluno> findByCpfContainingOrderByCpf(String cpf, Pageable pageable);

    @Transactional(readOnly = true)
    Aluno findByCpf(String cpf);

    @Transactional(readOnly = true)
    Aluno findByNome(String nome);

}
