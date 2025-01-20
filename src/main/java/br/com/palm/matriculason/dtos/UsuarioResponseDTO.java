package br.com.palm.matriculason.dtos;


import br.com.palm.matriculason.entities.Pessoas;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Pessoas pessoa;

    private boolean Status;

    private String username;
}
