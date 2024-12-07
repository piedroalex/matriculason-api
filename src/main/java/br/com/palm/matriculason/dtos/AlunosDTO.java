package br.com.palm.matriculason.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlunosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message="O nome é obrigatório")
    private String nome;

    @NotBlank(message="O cpf é obrigatório")
    private String cpf;

    @NotBlank(message="O email é obrigatório")
    private String email;

    @NotBlank(message="A matricula é obrigatória")
    private String matricula;

    @NotNull(message="O curso é obrigatório")
    private CursosDTO curso;
}
