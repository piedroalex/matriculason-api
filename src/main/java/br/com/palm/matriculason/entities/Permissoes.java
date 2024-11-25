package br.com.palm.matriculason.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * Entity para Cursos
 *
 * @author Gisele Santos
 */
@Entity @Data
public class Permissoes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A role da permissão é obrigatória") @Column(length = 50, unique = true)
    private String role;

    @NotBlank(message = "A descrição da permissão é obrigatória") @Column(length = 200, unique = true)
    private String descricao;

}
