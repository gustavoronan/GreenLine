package app.service;

import java.util.List;
import java.util.Optional;

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
	        // Salvar o produto no banco de dados
	        produtoRepository.save(produto);
	        // Gerar log antes de salvar o produto
	        String detalheProduto = produto.getNomeProduto();
	        String formato = "o produto: %s, foi adicionado";
	        String detalhes = String.format(formato, detalheProduto);
	        
	        logService.gerarLog("SAVE", "Produto", produto.getIdProduto(), detalhes, detalheProduto);

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
        
        // Atualizar o ID do produto no objeto produtoAtualizado
        produtoAtualizado.setIdProduto(idProduto);

        // Salvar o produto atualizado no repositório
        produtoRepository.save(produtoAtualizado);
        
        logService.gerarLog("UPDATE", "Produto", idProduto, detalhes, nomeNovo);

        return "O produto " + produtoAtualizado.getNomeProduto() + " foi atualizado com sucesso!";
    }
	
	
	public String delete(long idProduto) {
	    
	    Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
	    
	   
	    if (produtoOptional.isPresent()) {
	        Produto produto = produtoOptional.get();
	        String detalheProduto = produto.getNomeProduto();
	        String formato = "O produto: %s foi deletado";
	        String detalhes = String.format(formato, detalheProduto);
	        logService.gerarLog("DELETE", "Produto", idProduto, detalhes, detalheProduto);
	        
	        produtoRepository.deleteById(idProduto);
	        
	        return "Produto deletado com sucesso!";
	    } else {     
	        return "Produto não encontrado!";
	    }
	}

}
