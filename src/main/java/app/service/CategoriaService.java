package app.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Categoria;
import app.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public String save(Categoria categoria) {
		this.categoriaRepository.save(categoria);
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
		return categoria.getDescricao()+" Atualizada com sucesso";
	}
	
	public String delete (long idCategoria) {
		this.categoriaRepository.deleteById(idCategoria);;
		return " Categoria deletada com sucesso";
	}
	
	public List<Categoria> findByDescricao(String descricao){
		return this.categoriaRepository.findByDescricao(descricao);
	}
	
}
