package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.ItemCarrinho;
import app.repository.ItemCarrinhoRepository;

@Service
public class ItemCarrinhoService {
	
	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;
	
	public String save (ItemCarrinho itemCarrinho) {
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
		itemCarrinho.setIdItem(idItem);
		this.itemCarrinhoRepository.save(itemCarrinho);
		return "O " + itemCarrinho.getValorUnitario() + " Foi atualizado";
		
	}
	
	public String delete (long idItem) {
		this.itemCarrinhoRepository.deleteById(idItem);
		return " Venda deletada";
	}
}
