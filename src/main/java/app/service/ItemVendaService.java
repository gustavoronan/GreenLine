package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.ItemVenda;
import app.repository.ItemVendaRepository;

@Service
public class ItemVendaService {
	
	@Autowired
	private ItemVendaRepository itemVendaRepository;
	
	public String save (ItemVenda itemVenda) {
		this.itemVendaRepository.save(itemVenda);
		return itemVenda.getValorUnitario() + " Foi adicionado";
	}
	
	public List <ItemVenda> listAll () {
	return this.itemVendaRepository.findAll();
		
	}
	

	public ItemVenda findById(long idItemVenda) {

		ItemVenda itemVenda = this.itemVendaRepository.findById(idItemVenda).get();
		return itemVenda;

	}
	
	public String update (ItemVenda itemVenda, long idItemVenda) {
		itemVenda.setIdItemVenda(idItemVenda);
		this.itemVendaRepository.save(itemVenda);
		return "O " + itemVenda.getValorUnitario() + " Foi atualizado";
		
	}
	
	public String delete (long idItemVenda) {
		this.itemVendaRepository.deleteById(idItemVenda);
		return " Venda deletada";
	}
}
