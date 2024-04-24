package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Produto;
import app.repository.ProdutoRepository;

@SpringBootTest
public class ProdutoControllerTest {

	@Autowired
	ProdutoController produtoController;
	
	@MockBean
	ProdutoRepository produtoRepository;
	
	@BeforeEach
	void setup() {
		List<Produto> produto = new ArrayList<>();
		produto.add(new Produto(1L, "rtx", 5.000, "teste1", null, null, null));
		produto.add(new Produto(2L, "gtx", 6.000, "teste2", null, null, null));
		produto.add(new Produto(3L, "amd", 7.000, "teste3", null, null, null));
		
		when(produtoRepository.findAll()).thenReturn(produto);
	}
	
	
	@Test
	@DisplayName("Save esta funcionando correctamente")
	void testSaveCaso1() {
		Produto produto = new Produto(1L, "rtx", 4.000, "teste", null, null, null);
		when(produtoRepository.save(produto)).thenReturn(produto);
		ResponseEntity<String> response = produtoController.save(produto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	@DisplayName("listAll deve retornar os todos os itens")
	void testListAll() {
		ResponseEntity<List<Produto>> response = produtoController.listAll();
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(response.getBody().size(), 3);
	}
}
