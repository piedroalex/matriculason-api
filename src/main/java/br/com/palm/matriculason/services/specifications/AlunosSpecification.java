package br.com.palm.matriculason.services.specifications;

import br.com.palm.matriculason.entities.Alunos;

import br.com.palm.matriculason.filters.AlunosFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class AlunosSpecification {


    public static Specification<Alunos> filtrar(AlunosFilter alunosFilter) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (alunosFilter.getMatricula() != null && !alunosFilter.getMatricula().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("matricula"), "%" + alunosFilter.getMatricula() + "%"));
            }
            if (alunosFilter.getNome() != null && !alunosFilter.getNome().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("nome"), "%" + alunosFilter.getNome() + "%"));
            }
            if (alunosFilter.getCpf() != null && !alunosFilter.getCpf().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("cpf"), "%" + alunosFilter.getCpf() + "%"));
            }
            return predicate;
        };
    }
}
