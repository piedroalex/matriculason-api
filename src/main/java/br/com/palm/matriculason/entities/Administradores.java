package br.com.palm.matriculason.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @Entity @EqualsAndHashCode(callSuper = false)
public class Administradores extends Pessoas implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O cargo é obrigatório") @Column(length = 100, unique = true)
    private String cargo;

    @NotBlank(message = "O departamento é obrigatório") @Column(length = 100, unique = true)
    private String departamento;
}
