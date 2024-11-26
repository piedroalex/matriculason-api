package br.com.palm.matriculason.filters;

import br.com.palm.matriculason.entities.Cursos;
import lombok.Data;

@Data
public class AlunoFilter {

    private String nome = "";
    private String matricula = "";
    private String cpf = "";
    private String email = "";
    private Cursos curso = null;
}
