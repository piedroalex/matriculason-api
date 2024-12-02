package br.com.palm.matriculason.filters;

import lombok.Data;

/**
 * Filtro responsável por refinar a busca dos dados de permissoes
 *
 * @author Gisele Santos
 */
@Data
public class PermissoesFilter {

    private String role = "";
    private String descricao = "";
}
