package br.com.palm.matriculason.services.specifications;

import br.com.palm.matriculason.entities.Usuarios;
import br.com.palm.matriculason.filters.UsuariosFilter;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public class UsuariosSpecification {

    public static Specification<Usuarios> filtrar(UsuariosFilter usuariosFilter) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (usuariosFilter.getUsername() != null && !usuariosFilter.getUsername().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("username"), "%" + usuariosFilter.getUsername() + "%"));
            }

            if (usuariosFilter.getNome() != null && !usuariosFilter.getNome().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("pessoa").get("nome"), "%" + usuariosFilter.getNome() + "%"));
            }

            if (usuariosFilter.getStatus() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("status"), usuariosFilter.getStatus()));
            }

            return predicate;
        };
    }
}
