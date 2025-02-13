package br.com.palm.matriculason.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Alunos.class, name = "ALUNO"),
        @JsonSubTypes.Type(value = Administradores.class, name = "ADMIN")
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(of = { "id" })
public abstract class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank(message = "O nome é obrigatório") @Column(length = 100)
    private String nome;

    @CPF(message = "O cpf é obrigatório") @Column(length = 100, unique = true)
    private String cpf;

    @NotBlank(message = "O email é obrigatório") @Column(length = 100, unique = true)
    private String email;
}
