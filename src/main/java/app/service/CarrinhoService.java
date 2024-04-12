package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Carrinho;
import app.repository.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public String save (Carrinho carrinho) {
		this.carrinhoRepository.save(carrinho);
		return carrinho.getValorCarrinho() + "  registrada";
	}
	
	public List <Carrinho> listAll () {
		return this.carrinhoRepository.findAll();
	}
	

	public Carrinho findById(long idCarrinho) {
		Carrinho carrinho = this.carrinhoRepository.findById(idCarrinho).get();
		return carrinho;
	}
	
	public String update (Carrinho carrinho, long idCarrinho) {
		carrinho.setIdCarrinho(idCarrinho);
		this.carrinhoRepository.save(carrinho);
		return "O " + carrinho.getValorCarrinho() + " Venda atualizada";
	}
	
	public String delete (long idCarrinho) {
		this.carrinhoRepository.deleteById(idCarrinho);
		return " Venda deletada";
	}
	
}
