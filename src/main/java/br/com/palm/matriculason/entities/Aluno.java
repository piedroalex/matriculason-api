package br.com.palm.matriculason.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Aluno extends Pessoa implements Serializable {

    @NotBlank(message = "A matrícula é obrigatória") @Column(length = 100, unique = true)
    private String matricula;

    @NotNull(message = "O curso é obrigatório") @JoinColumn
    @ManyToOne
    private Cursos curso;
}
