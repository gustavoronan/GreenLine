package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.auth.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCliente;
	private String nomeCliente;
	private String telefoneCliente;
	private String enderecoCliente;

	//relacao de um cliente para um usuario
	@ManyToOne
	@JsonIgnoreProperties("cliente")
	private Usuario usuario;
}
