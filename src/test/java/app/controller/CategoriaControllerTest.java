package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Categoria;
import app.repository.CategoriaRepository;
import app.service.CategoriaService;

@SpringBootTest
public class CategoriaControllerTest {
	
	@Autowired
	CategoriaController categoriaController;
	
	@MockBean
	CategoriaRepository categoriaRepository;
	CategoriaService categoriaService;
	
	@BeforeEach
	void setup () {
		List<Categoria> listCategoria = new ArrayList<>();
		listCategoria.add(new Categoria(1, "intel"));
		listCategoria.add(new Categoria(2, "amd"));
		when(this.categoriaRepository.findAll()).thenReturn(listCategoria);
		
		//MOCK DE SAVE
		Categoria novoCategoria = new Categoria(3, "razer");
		when(this.categoriaRepository.save(novoCategoria)).thenReturn(novoCategoria);

		//MOCK DE FINDBYID
		Categoria objetoASerRetornado = new Categoria(2, "GHIABC");
		when(this.categoriaRepository.findById(1L)).thenReturn(Optional.of(objetoASerRetornado));

		}
	
	@Test
	void testListAll() {

	    ResponseEntity<List<Categoria>> listaCategoria = this.categoriaController.listAll();
	    assertEquals(2, listaCategoria.getBody().size());    
	}
	
	



	@Test
	void testSave() {
	    Categoria novoCategoria = new Categoria(3, "Gamer");


	    ResponseEntity<String> response = this.categoriaController.save(novoCategoria);

	    assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}
	
	@Test
	void testSaveNOTOK() {
		 Categoria categoriaInvalida = new Categoria();
		 categoriaInvalida.setDescricao(null);
		 
	    ResponseEntity<String> response = this.categoriaController.save(null);

	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}


	@Test
	void testFindByIdOk() {

	    ResponseEntity<Categoria> response = this.categoriaController.findById(1L);

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("GHIABC", response.getBody().getDescricao());

	}

	@Test
	void testFindByIdNOTOK() {

	    ResponseEntity<Categoria> response = this.categoriaController.findById(-1L);

	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

	@Test
	void testFindByIdOkSEMDADO() {

	    ResponseEntity<Categoria> response = this.categoriaController.findById(22222L);

	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}
	
	@Test
	void testUpdate() {
		
		Categoria categoriaExistente = new Categoria(4, "abc");
		Categoria categoriaAtualizada = new Categoria(1, "xyz");
	    long idCategoria = 1L;
		
		ResponseEntity<String> response = categoriaController.update(categoriaAtualizada, idCategoria);
	    
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	   
	    when(categoriaRepository.findById(idCategoria)).thenReturn(Optional.of(categoriaExistente));
	    when(categoriaRepository.save(categoriaAtualizada)).thenReturn(categoriaAtualizada);
	}
	
	@Test
	void testUpdateWithError() {
	    
	    Categoria categoriaExistente = new Categoria(2, "Notebook");
	    Categoria categoriaAtualizada = new Categoria(2, "Computador" );
	    
	    
	    long idCategoria = 1L;
	    
	    
	    when(categoriaRepository.findById(idCategoria)).thenReturn(Optional.of(categoriaExistente));
	    
	    
	    when(categoriaRepository.save(categoriaAtualizada)).thenThrow(new RuntimeException("Erro ao salvar a categoria"));
	    
	    
	    ResponseEntity<String> response = categoriaController.update(categoriaAtualizada, idCategoria);
	    
	   
	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	
	@Test
    void testDelete() {
        long idCategoria= 1L;
        ResponseEntity<String> response = categoriaController.delete(idCategoria);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(categoriaRepository).deleteById(idCategoria);
    }

	@Test
	void testDeleteNotOK() {
	    long idCategoria = 1L;

	    when(categoriaController.delete(idCategoria)).thenThrow(new RuntimeException("Exceção simulada"));

	    ResponseEntity<String> response = categoriaController.delete(idCategoria);

	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void testFindByDescricao() {
	    // Dado
	    String descricao = "Gamer";
	    List<Categoria> categorias = new ArrayList<>();
	    categorias.add(new Categoria(1, "Gamer"));
	    when(categoriaRepository.findByDescricao(descricao)).thenReturn(categorias);

	    // Quando
	    ResponseEntity<List<Categoria>> response = categoriaController.findByDescricao(descricao);

	    // Então
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(1, response.getBody().size());
	    assertEquals(descricao, response.getBody().get(0).getDescricao());
	}
	
}
