package br.com.palm.matriculason.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO responsável por receber os dados de um objeto Cursos.
 * @author Pedro Alex
 */
@Data @EqualsAndHashCode
public class CursosDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message="O nome é obrigatório")
	private String nome;
			
}
