package br.com.palm.matriculason.filters;
import lombok.Data;

@Data
public class UsuariosFilter {

    private String nome = "";
    private String username = ""; 
    private Boolean status = null;

}