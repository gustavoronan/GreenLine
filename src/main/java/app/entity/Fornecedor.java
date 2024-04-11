package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


public class Fornecedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFornecedor;
	
	@NotBlank(message = "O campo cnpj não pode ser vazio")
	private String cnpj;
	
	@NotBlank(message = "O campo nome não pode ser vazio")
	private String nomeFornecedor;
	
	@NotBlank(message = "O campo email não pode ser vazio")
	private String emailFornecedor;
	
	//@OneToMany (mappedBy = "Fornecedor")
	//private List<Produto> produto;
}
