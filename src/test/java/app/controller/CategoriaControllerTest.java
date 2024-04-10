package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@SpringBootTest
public class CategoriaControllerTest {
	
	@Autowired
	CategoriaController categoriaController;
	
	@MockBean
	CategoriaRepository categoriaRepository;
	
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
	void cenario01() {

	    ResponseEntity<List<Categoria>> listaCategoria = this.categoriaController.listAll();
	    assertEquals(2, listaCategoria.getBody().size());    
	}


	@Test
	void testSave() {
	    Categoria novoCategoria = new Categoria(3, null);


	    ResponseEntity<String> response = this.categoriaController.save(novoCategoria);

	    assertEquals(HttpStatus.CREATED, response.getStatusCode());
	   // assertEquals(" Categoria salvo!", response.getBody());

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
	
	/*@Test
    void testDelete() {
        long idVenda = 1L;
        ResponseEntity<String> response = categoriaController.delete(idVenda);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(categoriaRepository).deleteById(idVenda);
    }*/

}
