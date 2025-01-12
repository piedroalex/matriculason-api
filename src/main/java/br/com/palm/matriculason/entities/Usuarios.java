package br.com.palm.matriculason.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Data @Entity
public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotBlank(message = "O username é obrigatório") @Column(length = 50, unique = true)
    private String username;

    @NotBlank(message = "A senha é obrigatória") @Column(length = 100)
    private String senha;

    @Column(nullable = false)
    private Boolean status = true;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    private Pessoas pessoa;



}
