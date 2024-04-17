package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Carrinho;
import app.entity.ItemCarrinho;
import app.entity.Produto;

@SpringBootTest
public class CarrinhoServiceTeste {

	@Autowired
	CarrinhoService carrinhoService;
	
	@Test
	@DisplayName("TEST DO METODO VALORTOTALCARRINHO QUE CALCULA O VALORCARRINHO")
	void cenario1() {
		// Cria um produto valido
		Produto produto1 = new Produto(1, "Placa de video", 2000.0, "asdmmoaom asdasd ", null, null, null);
		Produto produto2 = new Produto(2, "Monitor", 999.99, "bbbrbaasda adsae eeafasd", null, null, null);
		//cria uma lista de itens para adicionar ao carrinho
		List<ItemCarrinho> list = new ArrayList<>();
        list.add(new ItemCarrinho(1, 2000.0, 2, produto1, null));
        list.add(new ItemCarrinho(2, 999.99, 1, produto2, null));
        
        double valorFinal = this.carrinhoService.valorTotalCarrinho(list);
        
        assertEquals(4999.99, valorFinal);
	}
	
//	@Test
//	@DisplayName("TEST DO METODO VALORTOTALCARRINHO QUE CALCULA O VALORCARRINHO")
//	void cenario2() {
//		// Cria um produto valido
//		//Produto produto1 = new Produto(1, "Placa de video", 2000.0, "asdmmoaom asdasd ", null, null, null);
//		//Produto produto2 = new Produto(2, "Monitor", 999.99, "bbbrbaasda adsae eeafasd", null, null, null);
//		//cria uma lista de itens para adicionar ao carrinho
//		List<ItemCarrinho> list = new ArrayList<>();
//        list.add(new ItemCarrinho(1, 2000.0, 2, null, null));
//        list.add(new ItemCarrinho(2, 999.99, 1, null, null));
//        
//        //double valorFinal = this.carrinhoService.valorTotalCarrinho(list);
//        
//        assertThrows(Exception.class, () -> {
//            this.carrinhoService.valorTotalCarrinho(new ArrayList<>());
//        }, "Poduto inexistente");
//	}
	
}
