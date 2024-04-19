package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
	        list.add(new Carrinho(1, "carrinho do usuario 1", 200.05, null, null));
	        list.add(new Carrinho(2, "carrinho do usuario 2", 150.05, null, null));
	        when(this.carrinhoRepository.findAll()).thenReturn(list);
	        
	      //MOCK DE SAVE
	        Carrinho carrinhoS = new Carrinho(3, "carrinho do usuario 3", 50.05, null, null);
	        when(this.carrinhoRepository.save(carrinhoS)).thenReturn(carrinhoS);

	        //MOCK DE FINDBYID
	        Carrinho carrinhoF = new Carrinho(4, "carrinho do usuario 4", 69.99, null, null);
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
	 @DisplayName("LIST ALL MOCK ERRO TEST")
     void cenario2() {

         ResponseEntity<List<Carrinho>> list = this.carrinhoController.listAll();
         assertNotEquals(0, list.getBody().size());  
      
     }
	 
	 @Test
	 @DisplayName("SAVE MOCK  TEST BAD")
     void cenario3() {
		 
		 Carrinho carrinho = new Carrinho(5, "carrinho do usuario 3", 50.05, null, null);
		
		 ResponseEntity <String> response = this.carrinhoController.save(carrinho);
         
         assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
         
     }
	 //precisa passar pelo menos um itemCarrinho para salvar
	 @Test
	 @DisplayName("SAVE MOCK TEST OK")
	 void cenario4() {
		
		 // Cria um produto valido
		 Produto produto = new Produto(1, "Placa de video", 2000.0, "asdmmoaom asdasd ", null, null, null);
		 // Cria um itemCarrinho válido
		 ItemCarrinho itemCarrinho = new ItemCarrinho(1, 20.0, 2, produto, null);

		 // Cria um carrinho e adiciona o itemCarrinho
		 Carrinho carrinho = new Carrinho(5, "carrinho do usuario 3", 40.0, List.of(itemCarrinho), null);
		 
		 ResponseEntity <String> response = this.carrinhoController.save(carrinho);
		 
		 assertEquals(HttpStatus.OK, response.getStatusCode());
		 
	 }
	 
}
