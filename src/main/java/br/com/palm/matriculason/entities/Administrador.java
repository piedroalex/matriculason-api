package br.com.palm.matriculason.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Administrador extends Pessoa implements Serializable {

    @NotBlank(message = "O cargo é obrigatório") @Column(length = 100, unique = true)
    private String cargo;

    @NotBlank(message = "O departamento é obrigatório") @Column(length = 100, unique = true)
    private String departamento;
}
