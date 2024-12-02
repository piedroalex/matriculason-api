package br.com.palm.matriculason.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome de usuário é obrigatório")
    @Column(length = 50, unique = true)
    private String username;

    @NotBlank(message = "A senha é obrigatória")
    @Column(length = 100)
    private String senha;

    @Column(nullable = false)
    private Boolean status;

    @NotBlank(message = "O campo pessoa é obrigatório")
    @Column(length = 150)
    private String pessoa;
}
