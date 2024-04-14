package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Carrinho;
import app.repository.CarrinhoRepository;
import jakarta.validation.Valid;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public double valorTotalCarrinho(@Valid List<ItemCarrinho> itemCarrinho) {
		
		double valorTotal = 0;
		if(itemCarrinho != null) {
			for (@Valid ItemCarrinho itemCarrinho : itemCarrinho) {
				valorTotal += (itemCarrinho.getvalorUnitario() * itemCarrinho.getquantProd());
			}
		}
		return valorTotal;
		
	}
	
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
	
	//consultas DB
	
	public List<Carrinho> findByCarrinho(long idCarrinho){
		Carrinho carrinho = new Carrinho();
		carrinho.setIdCarrinho(idCarrinho);
		return this.carrinhoRepository.findByCarrinho(carrinho);
	}
	
	public List<Carrinho> findByProdutoNomeProduto(String nomeProduto){
		return this.carrinhoRepository.findByProdutoNomeProduto(nomeProduto);
	}
	
	//testar apos merge
	public List<Carrinho> findByIdCarrinho(long idCarrinho){
		return this.carrinhoRepository.findByIdCarrinho(idCarrinho);
	}
	
	public List<Carrinho> buscarVendaAcimaValor(double valorCarrinho){
		return this.carrinhoRepository.buscarVendaAcimaValor(valorCarrinho);
	}
	
	public List<Carrinho> buscarVendaAbaixoValor(double valorCarrinho){
		return this.carrinhoRepository.buscarVendaAbaixoValor(valorCarrinho);
	}
}
