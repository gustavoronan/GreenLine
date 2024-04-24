package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
	
	
	
	@Test
	@DisplayName("Save esta funcionando correctamente")
	void testSaveCaso1() {
		Produto produto = new Produto(1L, "rtx", 4.000, "teste", null, null, null);
		when(produtoRepository.save(produto)).thenReturn(produto);
		ResponseEntity<String> response = produtoController.save(produto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
}
