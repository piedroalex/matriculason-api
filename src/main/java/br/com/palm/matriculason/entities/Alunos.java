package br.com.palm.matriculason.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;

@Data @Entity @EqualsAndHashCode(callSuper = false)
public class Alunos extends Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "A matrícula é obrigatória") @Column(length = 100, unique = true)
    private String matricula;

    @NotNull(message = "O curso é obrigatório")
    @ManyToOne(cascade = CascadeType.DETACH)
    private Cursos curso;
}
