package br.com.palm.matriculason.dtos;

import br.com.palm.matriculason.entities.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Pessoa pessoa;

    @NotBlank(message = "O nome de usuário é obrigatório")
    private String username;

    @NotNull(message = "O status é obrigatório")
    private Boolean status;

}
