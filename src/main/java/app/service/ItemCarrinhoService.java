package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Carrinho;
import app.entity.ItemCarrinho;
import app.entity.Produto;
import app.entity.Usuario;
import app.repository.CarrinhoRepository;
import app.repository.ItemCarrinhoRepository;
import jakarta.validation.Valid;

@Service
public class ItemCarrinhoService {
	
	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	
	public double setValor(@Valid Produto produto) {
		
		double valorUnitario = 0;
		if (produto !=null) {
			valorUnitario = produto.getValorProduto();
		}
		return valorUnitario;
	}
	
	
	public String save (ItemCarrinho itemCarrinho) {
		//adiciona o valor do produto a variavel valorUnitario para manter o registro do valor na compra
		double valorUnitario = this.setValor(itemCarrinho.getProduto());
		itemCarrinho.setValorUnitario(valorUnitario);
		System.out.println(itemCarrinho.getIdItem());
		this.itemCarrinhoRepository.save(itemCarrinho);
		return itemCarrinho.getValorUnitario() + " Foi adicionado";
	}
	
	public List <ItemCarrinho> listAll () {
		return this.itemCarrinhoRepository.findAll();
	}

	public ItemCarrinho findById(long idItem) {

		ItemCarrinho itemCarrinho = this.itemCarrinhoRepository.findById(idItem).get();
		return itemCarrinho;

	}
	
	public String update (ItemCarrinho itemCarrinho, long idItem) {
		Optional<ItemCarrinho> itemcar = this.itemCarrinhoRepository.findById(idItem);
		if (itemcar.isEmpty()) {
			throw new RuntimeException("Id nao encontrado");
		}
		double valorUnitario = this.setValor(itemCarrinho.getProduto());
		itemCarrinho.setValorUnitario(valorUnitario);
		itemCarrinho.setIdItem(idItem);
		this.itemCarrinhoRepository.save(itemCarrinho);
		return "O item" + itemCarrinho.getValorUnitario() + " Foi atualizado";
		
	}
	
	public String delete (long idItem) {
		Optional<ItemCarrinho> itemcar = this.itemCarrinhoRepository.findById(idItem);
		if (itemcar.isEmpty()) {
			throw new RuntimeException("Id nao encontrado");
		}
		this.itemCarrinhoRepository.deleteById(idItem);
		return " Produto deletado";
	}
	
	public Carrinho getCarrinhoByUser (long idUsuario) {
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(idUsuario);
		Carrinho carrinho = this.carrinhoRepository.getCarrinhoAbertoDoUsuario(usuario);
		
		if(carrinho == null) {
			Carrinho carrinhoNovo = new Carrinho();
			carrinhoNovo.setUsuario(usuario);
			carrinhoNovo.setStatus("Em aberto");
			carrinhoNovo.setDescricaoCarrinho("---");
			carrinhoNovo.setValorCarrinho(0);
			this.carrinhoRepository.save(carrinhoNovo);
			carrinho = this.carrinhoRepository.getCarrinhoAbertoDoUsuario(usuario);
		}

		return carrinho;
	}
}
