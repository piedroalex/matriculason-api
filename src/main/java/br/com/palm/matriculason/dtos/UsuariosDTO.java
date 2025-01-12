package br.com.palm.matriculason.dtos;

import br.com.palm.matriculason.entities.Pessoas;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.postgresql.util.LruCache;

import java.io.Serializable;

@Data
public class UsuariosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Pessoas pessoa;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, max = 12, message = "A senha deve ter entre 8 e 12 caracteres")
    private String senha;

    @Size(min = 8, max = 12, message = "A confirmação de senha deve ter entre 8 e 12 caracteres")
    @JsonIgnore
    private String confirmarSenha;
}
