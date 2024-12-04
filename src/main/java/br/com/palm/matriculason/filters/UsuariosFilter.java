package br.com.palm.matriculason.filters;
import lombok.Data;

@Data
public class UsuariosFilter {

    private String nome = "";
    private String username = ""; 
    private String email = "";
    private Boolean status = null;

}