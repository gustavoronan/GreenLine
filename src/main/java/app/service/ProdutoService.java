package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Produto;
import app.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public String save(Produto produto) {
		produtoRepository.save(produto);
		return produto.getNomeProduto() + " salvo com sucesso!";
	}
	
	public List<Produto> listAll() {
		return produtoRepository.findAll();
	}
	
	public Produto findById(long idProduto) {
		return produtoRepository.findById(idProduto).get();
	}
	
	public String update(long idProduto, Produto produto) {
		produto.setIdProduto(idProduto);
		produtoRepository.save(produto);
		return "O produto " + produto.getNomeProduto() + " foi atualizado com sucesso!";
	}
	
	public String delete(long idProduto) {
		produtoRepository.deleteById(idProduto);
		return "Produto deletado com sucesso!";
	}
}
