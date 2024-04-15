package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.ItemCarrinho;
import app.entity.Produto;
import app.repository.ItemCarrinhoRepository;
import jakarta.validation.Valid;

@Service
public class ItemCarrinhoService {
	
	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;
	
	public double setValor(@Valid Produto produto) {
		
		double valorUnitario = 0;
		if (produto !=null) {
			valorUnitario = produto.getValorProduto();
		}
		return valorUnitario;
	}
	
	
	public String save (ItemCarrinho itemCarrinho) {
		double valorUnitario = this.setValor(itemCarrinho.getProduto());
		itemCarrinho.setValorUnitario(valorUnitario);
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
		double valorUnitario = this.setValor(itemCarrinho.getProduto());
		itemCarrinho.setValorUnitario(valorUnitario);
		itemCarrinho.setIdItem(idItem);
		this.itemCarrinhoRepository.save(itemCarrinho);
		return "O " + itemCarrinho.getValorUnitario() + " Foi atualizado";
		
	}
	
	public String delete (long idItem) {
		this.itemCarrinhoRepository.deleteById(idItem);
		return " Venda deletada";
	}
}
