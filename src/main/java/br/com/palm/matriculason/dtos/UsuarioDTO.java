package br.com.palm.matriculason.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "O campo pessoa é obrigatório")
    private String pessoa;

    @NotBlank(message = "O nome de usuário é obrigatório")
    private String username;

    @NotNull(message = "O status é obrigatório")
    private Boolean status;

}
