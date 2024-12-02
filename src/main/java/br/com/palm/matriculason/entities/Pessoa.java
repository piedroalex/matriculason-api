package br.com.palm.matriculason.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank(message = "O nome é obrigatório") @Column(length = 100)
    private String nome;

    @NotBlank(message = "O cpf é obrigatório") @Column(length = 100, unique = true)
    private String cpf;

    @NotBlank(message = "O email é obrigatório") @Column(length = 100, unique = true)
    private String email;
}
