package app.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Categoria;
import app.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LogService logService;
	
	public String save(Categoria categoria) {
		this.categoriaRepository.save(categoria);
		// Salvar o produto no banco de dados
        categoriaRepository.save(categoria);
        // Gerar log antes de salvar o produto
        String detalheCategoria = categoria.getDescricao();
        String formato = "a descrição: %s, foi criada";
        String detalhes = String.format(formato, detalheCategoria);
        logService.gerarLog("SAVE", "Categoria", categoria.getIdCategoria(), detalhes, detalheCategoria);
		return categoria.getDescricao()+" descrição adicionada";
	}
	
	public List<Categoria> listAll() {
		return this.categoriaRepository.findAll();
	}
	
	public Categoria findById(long idCategoria) {
		Categoria categoria = this.categoriaRepository.findById(idCategoria).get();
		return categoria;
	}
	
	public String update (long id, Categoria categoria) {
		categoria.setIdCategoria(id);
		this.categoriaRepository.save(categoria);
		
		String detalheCategoria = categoria.getDescricao();
        String formato = "a descrição: %s, foi Alterada";
        String detalhes = String.format(formato, detalheCategoria);
        logService.gerarLog("UPDATE", "Categoria", categoria.getIdCategoria(), detalhes, detalheCategoria);
		
		return categoria.getDescricao()+" Atualizada com sucesso";
	}
	
	public String delete (long idCategoria) {
		this.categoriaRepository.deleteById(idCategoria);
		
		    
		    Optional<Categoria> categoriaOp = categoriaRepository.findById(idCategoria);
		    
		   
		    if (categoriaOp.isPresent()) {
		        Categoria categoria = categoriaOp.get();
		        String detalheCategoria = categoria.getDescricao();
		        
		        
		        categoriaRepository.deleteById(idCategoria);
		        
		        
		        String formato = "A categoria: %s foi deletado";
		        String detalhes = String.format(formato, detalheCategoria);
		        logService.gerarLog("DELETE", "Categoria", idCategoria, detalhes, detalheCategoria);
		        
		        System.out.println("Categoria deletado: " + detalheCategoria);
		        return "Produto deletado com sucesso!";
		    } else {
		        
		        return "Produto não encontrado!";
		    }
		
	}
	
	
	public List<Categoria> findByDescricao(String descricao){
		return this.categoriaRepository.findByDescricao(descricao);
	}
	
}

