package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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


public class ItemCarrinho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idItem; // se trata de um item de um carrinho 
	// serve para duplicar o valor do produto para o caso de que exista 
	// alguma alteração futura na tabela de produtos
	@NotNull(message = "valor unitario não pode ser nulo")
	private double valorUnitario; 
	@NotNull(message = "quantidade de produtos não pode ser nula")
	private int quantProd;
	
	@OneToMany (mappedBy = "itemCarrinho")
	@JsonIgnoreProperties("itemCarrinho")
	private Produto produto;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JsonIgnoreProperties("itemCarrinho")
	private Carrinho carrinho;
	
}
