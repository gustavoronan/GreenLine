package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Categoria;


@SpringBootTest
public class CategoriaServiceTest {
	/*@Autowired
	VendaService vendaService;
	
	@Test
	void cenario01() {
		List<Categoria> listaProd = new ArrayList<>();
		listaProd.add(new Categoria(1, "computador"));
		listaProd.add(new Categoria(2, "celular"));
		double resultado = this.vendaService.calcularValorTotal(listaProd);
		assertEquals(100, resultado);
	}*/
}
