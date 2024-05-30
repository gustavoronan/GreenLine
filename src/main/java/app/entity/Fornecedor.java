package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@JsonIgnoreProperties ("fornecedor")
	@OneToMany (mappedBy = "fornecedor")
	private List<Produto> produto;
}