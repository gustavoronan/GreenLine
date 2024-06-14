package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Carrinho;
import app.entity.ItemCarrinho;
import app.entity.Produto;
import app.entity.Usuario;
import app.repository.CarrinhoRepository;

@SpringBootTest
public class CarrinhoControllerTest {

	@Autowired
	CarrinhoController carrinhoController;
	
	@MockBean
	CarrinhoRepository carrinhoRepository;
	
	 @BeforeEach
	    void setup () {
	        List<Carrinho> list = new ArrayList<>();
	        list.add(new Carrinho(1, "carrinho do usuario 1", 200.05, null, null, null, null));
	        list.add(new Carrinho(2, "carrinho do usuario 2", 150.05, null, null, null, null));
	        when(this.carrinhoRepository.findAll()).thenReturn(list);
	        
	        
	      //MOCK DE SAVE
	        Carrinho carrinhoS = new Carrinho(3, "carrinho do usuario 3", 50.05, null, null, null, null);
	        when(this.carrinhoRepository.save(carrinhoS)).thenReturn(carrinhoS);

	        //MOCK DE FINDBYID
	        Carrinho carrinhoF = new Carrinho(4, "carrinho do usuario 4", 69.99, null, null, null, null);
	        when(this.carrinhoRepository.findById(1L)).thenReturn(Optional.of(carrinhoF));
	       

	        }
	 
	 @Test
	 @DisplayName("LIST ALL MOCK TEST")
     void cenario1() {

         ResponseEntity<List<Carrinho>> list = this.carrinhoController.listAll();
         assertEquals(2, list.getBody().size());  
         assertEquals(HttpStatus.OK, list.getStatusCode());
     }
	 
	 @Test
	 @DisplayName("SAVE MOCK  TEST ERRO")
     void cenario3() {
		 
		 Carrinho carrinho = new Carrinho(5, "carrinho do usuario 3", 50.05, null, null, null, null);
		 when(this.carrinhoRepository.save(carrinho)).thenReturn(carrinho);
		 
		 ResponseEntity <String> response = this.carrinhoController.save(carrinho);
         
         assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
         
     }

	 //precisa passar pelo menos um itemCarrinho para salvar
	 @Test
	 @DisplayName("SAVE MOCK TEST OK")
	 void cenario4() {
		
		 // Cria um itemCarrinho válido
		 ItemCarrinho itemCarrinho = new ItemCarrinho(1, 2000.0, 2, null, null);
		 // Cria um usuario valido
		 Usuario usuario = new Usuario(1,"234234234", "2324234234", null,null);

		 // Cria um carrinho e adiciona o itemCarrinho e usuario
		 Carrinho carrinho = new Carrinho(6, "carrinho do usuario 3", 4000.0, "Encerrado", null, List.of(itemCarrinho), usuario);
		 when(this.carrinhoRepository.save(carrinho)).thenReturn(carrinho);
		 
		 ResponseEntity <String> response = this.carrinhoController.save(carrinho);
		 
		 assertEquals(HttpStatus.CREATED, response.getStatusCode());
		 
	 }
	 
//	 @Test
//	 @DisplayName("UPDATE MOCK TEST OK")
//	 void cenario5() {
//
//		// Cria um itemCarrinho válido
//		ItemCarrinho itemCarrinho = new ItemCarrinho(1, 2000.0, 2, null, null);
//		// Cria um usuario valido
//		Usuario usuario = new Usuario(1,"234234234", "2324234234", null,null);
//				 
//		// Cria um carrinho e adiciona o itemCarrinho e usuario
//		Carrinho carrinho = new Carrinho(1, "carrinho do usuario 1", 4000.0, null, null, List.of(itemCarrinho), usuario);
//		
//		ItemCarrinho itemCarrinhoUpd = new ItemCarrinho(1, 2000.0, 1, null, null);
//		Carrinho carrinhoUpd = new Carrinho(1, "carrinho do usuario 1", 2000.0, "Em Aberto", null, List.of(itemCarrinhoUpd), usuario);
//		
//		long idCarrinho = 1L;
//			
//		ResponseEntity<String> response = carrinhoController.update(carrinhoUpd, idCarrinho);
//		
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		   
//     }
	 
	 @Test
	 @DisplayName("UPDATE MOCK TEST ERRO")
	 void cenario6() {

		// Cria um produto valido
		Produto produto = new Produto(1, "Placa de video", 2000.0, "asdmmoaom asdasd ", null, null, null);
		// Cria um itemCarrinho válido
		ItemCarrinho itemCarrinho = new ItemCarrinho(1, 2000.0, 2, produto, null);

		// Cria um carrinho e adiciona o itemCarrinho
		Carrinho carrinho = new Carrinho(1, "carrinho do usuario 3", 4000.0, null, null, List.of(itemCarrinho), null);
		
		ItemCarrinho itemCarrinhoUpd = new ItemCarrinho(1, 2000.0, 1, produto, null);
		Carrinho carrinhoUpd = new Carrinho(1, "carrinho do usuario 3", 2000.0, null, null, List.of(itemCarrinhoUpd), null);
		
		long idCarrinho = 2L;
			
		ResponseEntity<String> response = carrinhoController.update(carrinhoUpd, idCarrinho);
		
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
		   
     }
	 
	 @Test
	 @DisplayName("FINDBYID MOCK TEST OK")
	 void cenario7() {

		 ResponseEntity<Carrinho> response = this.carrinhoController.findById(1L);

		 assertEquals(HttpStatus.OK, response.getStatusCode());
		 assertEquals(69.99, response.getBody().getValorCarrinho());

	 }
	 
	 @Test
	 @DisplayName("FINDBYID MOCK TEST ERRO")
	 void cenario8() {

		 ResponseEntity<Carrinho> response = this.carrinhoController.findById(3L);

		 assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());

	 }
	 
	 @Test
	 @DisplayName("findByNomeProduto MOCK TEST ")
	 void cenario9() {
		 
		// Cria um produto valido
		Produto produto = new Produto(1, "Placa de video", 2000.0, "asdmmoaom asdasd ", null, null, null);
		// Cria um itemCarrinho válido
		ItemCarrinho itemCarrinho = new ItemCarrinho(1, 2000.0, 2, produto, null);

		// Cria um carrinho e adiciona o itemCarrinho
		Carrinho carrinho = new Carrinho(1, "carrinho do usuario 3", 4000.0, null, null, List.of(itemCarrinho), null);
		when(this.carrinhoRepository.findByItemCarrinhoProdutoNomeProduto("Placa de video")).thenReturn(List.of(carrinho)); 
		
		 String nomeP = "Placa de video";
		 ResponseEntity <List<Carrinho>> response = this.carrinhoController.findByItemCarrinhoProdutoNomeProduto(nomeP);

		 assertEquals(HttpStatus.OK, response.getStatusCode());

	 }
	 
	 @Test
	 @DisplayName("findByNomeProduto MOCK TEST")
	 void cenario10() {
	     // Cria um produto válido
	     Produto produto = new Produto(1, "Placa de video", 2000.0, "asdmmoaom asdasd ", null, null, null);
	     // Cria um itemCarrinho válido
	     ItemCarrinho itemCarrinho = new ItemCarrinho(1, 2000.0, 2, produto, null);

	     // Cria um carrinho e adiciona o itemCarrinho
	     Carrinho carrinho = new Carrinho(1, "carrinho do usuario 3", 4000.0, null, null, List.of(itemCarrinho), null);
	     when(this.carrinhoRepository.findByItemCarrinhoProdutoNomeProduto("")).thenReturn(List.of(carrinho));
	     
	     // Realiza a chamada ao método de busca
	     String nomeP = "";
	     ResponseEntity<List<Carrinho>> response = this.carrinhoController.findByItemCarrinhoProdutoNomeProduto(nomeP);
	     
	     
	     assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	 }
	 
	 @Test
	 @DisplayName("buscarVendaAbaixoValor ALL MOCK TEST")
	 void cenario11() {
		 List<Carrinho> list = new ArrayList<>();
	     list.add(new Carrinho(1, "carrinho do usuario 1", 200.05, null, null, null, null));
	     list.add(new Carrinho(2, "carrinho do usuario 2", 150.05, null, null, null, null));
	     when(this.carrinhoRepository.buscarVendaAbaixoValor(250)).thenReturn(list);
	     
		 ResponseEntity<List<Carrinho>> lista = this.carrinhoController.buscarVendaAbaixoValor(250);
		 
		 assertEquals(2, lista.getBody().size());  
		 assertEquals(HttpStatus.OK, lista.getStatusCode());
	 }
	 
	 @Test
	 @DisplayName("buscarVendaAcimaValor ALL MOCK TEST")
	 void cenario12() {
		 List<Carrinho> list = new ArrayList<>();
	     list.add(new Carrinho(1, "carrinho do usuario 1", 200.05, null, null, null, null));
	     list.add(new Carrinho(2, "carrinho do usuario 2", 150.05, null, null, null, null));
	     when(this.carrinhoRepository.buscarVendaAcimaValor(100)).thenReturn(list);
	     
		 ResponseEntity<List<Carrinho>> lista = this.carrinhoController.buscarVendaAcimaValor(100);
		 
		 assertEquals(2, lista.getBody().size());  
		 assertEquals(HttpStatus.OK, lista.getStatusCode());
	 }
	
}
