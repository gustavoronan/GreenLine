package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrinho {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCarrinho;
	@NotBlank (message = " Descricao nao pode estar vazio")
	private String descricaoCarrinho;
	@NotNull (message = " Valor nao pode estar nulo")
	private double valorCarrinho;
	
	@OneToMany (mappedBy = "carrinho")
	@JsonIgnoreProperties ("carrinho")
	private List<ItemCarrinho> itemCarrinho;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JsonIgnoreProperties ("carrinho")
	private Usuario usuario;
	
}