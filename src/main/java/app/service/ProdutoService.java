package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import app.entity.Log;
import app.entity.Produto;
import app.repository.LogRepository;
import app.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private LogService logService;

	
	 public String save(Produto produto) {

	        // Gerar log antes de salvar o produto
	        logService.gerarLog("SAVE", "Produto", produto.getIdProduto(), null);

	        // Salvar o produto no banco de dados
	        produtoRepository.save(produto);

	        return produto.getNomeProduto() + " salvo com sucesso!";
	    }
	
	public List<Produto> listAll() {
		return produtoRepository.findAll();
	}
	
	public Produto findById(long idProduto) {
		return produtoRepository.findById(idProduto).get();
	}
	
	public String update(long idProduto, Produto produtoAtualizado) {
		
		// Buscar o produto atual do banco de dados
        Produto produtoAntigo = produtoRepository.findById(idProduto).get();
        String nomeAntigo = produtoAntigo.getNomeProduto();
        String nomeNovo = produtoAtualizado.getNomeProduto();
        double valorAntigo = produtoAntigo.getValorProduto();
        double valorNovo = produtoAtualizado.getValorProduto();
        String formato = "Atualizado: Nome antigo = %s, Nome novo = %s; Preço antigo = %.2f, Preço novo = %.2f";
        String detalhes = String.format(formato, nomeAntigo, nomeNovo, valorAntigo, valorNovo);
        
        System.out.println("Produto antigo: Nome = " + produtoAntigo.getNomeProduto() + ", Preço = " + produtoAntigo.getValorProduto());
        // Atualizar o ID do produto no objeto produtoAtualizado
        produtoAtualizado.setIdProduto(idProduto);
        System.out.println("Produto atualizado: Nome = " + produtoAtualizado.getNomeProduto() + ", Preço = " + produtoAntigo.getValorProduto());

        // Salvar o produto atualizado no repositório
        produtoRepository.save(produtoAtualizado);
        System.out.println("Detalhes do log: " + detalhes);
        logService.gerarLog("UPDATE", "Produto", idProduto, detalhes);

        return "O produto " + produtoAtualizado.getNomeProduto() + " foi atualizado com sucesso!";
    }
	
	
	public String delete(long idProduto) {
		logService.gerarLog("DELETE", "Produto", idProduto, null);
		produtoRepository.deleteById(idProduto);
		return "Produto deletado com sucesso!";
	}
}
