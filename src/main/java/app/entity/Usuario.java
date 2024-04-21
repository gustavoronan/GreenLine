package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	@NotBlank(message = "Email do cliente nao pode estar vazio")
	private String emailUsuario;
	@NotBlank(message = "Senha do cliente nao pode estar vazio")
	private String senhaUsuario;
	
	//relacao de um cliente para muitas vendas
		@OneToMany(mappedBy = "usuario")
		@JsonIgnoreProperties("usuario")
		private List<Carrinho> carrinho;
		
		@OneToOne(mappedBy = "usuario")
		@JsonIgnoreProperties("usuario")
		//@JoinColumn(name = "id_cliente")//Define a coluna de chave estrangeira na tabela de Usuario
		private Cliente cliente;

}
