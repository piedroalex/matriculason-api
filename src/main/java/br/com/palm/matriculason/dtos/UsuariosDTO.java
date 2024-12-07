package br.com.palm.matriculason.dtos;

import br.com.palm.matriculason.entities.Pessoas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UsuariosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Pessoas pessoa;

    @NotBlank(message = "O nome de usuário é obrigatório")
    private String username;

    @NotNull(message = "O status é obrigatório")
    private Boolean status;

}
