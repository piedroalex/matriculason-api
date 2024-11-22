package br.com.palm.matriculason.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Entity para Cursos
 *
 * @author Pedro Alex
 */
@Entity @Data
public class Cursos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do curso é obrigatório") @Column(length = 100, unique = true)
    private String nome;

}