package br.com.palm.matriculason.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * DTO responsável por receber os dados de um objeto Permissao.
 * @author Gisele Santos
 */
@Data @EqualsAndHashCode
public class PermissoesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "A role é obrigatória")
    private String role;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
}
