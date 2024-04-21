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

import app.entity.ItemCarrinho;
import app.repository.ItemCarrinhoRepository;

@SpringBootTest
public class ItemCarrinhoControllerTest {

    @Autowired
    ItemCarrinhoController itemCarrinhoController;

    @MockBean
    ItemCarrinhoRepository itemCarrinhoRepository;

    @BeforeEach
    void setup() {
        List<ItemCarrinho> listItemCarrinho = new ArrayList<>();
        listItemCarrinho.add(new ItemCarrinho(1, 32, 4, null, null));
        listItemCarrinho.add(new ItemCarrinho(2, 32, 4, null, null));
        when(this.itemCarrinhoRepository.findAll()).thenReturn(listItemCarrinho);

        // MOCK DE SAVE
        ItemCarrinho novoItemCarrinho = new ItemCarrinho(3, 15, 2, null, null);
        when(this.itemCarrinhoRepository.save(novoItemCarrinho)).thenReturn(novoItemCarrinho);

        // MOCK DE FINDBYID
        ItemCarrinho objetoASerRetornado = new ItemCarrinho(4, 45, 23, null, null);
        when(this.itemCarrinhoRepository.findById(1L)).thenReturn(Optional.of(objetoASerRetornado));
    }

    // SAVE

    @Test
    void testSave() {
        ItemCarrinho novoItemCarrinho = new ItemCarrinho(4, 23, 3, null, null);

        ResponseEntity<String> response = this.itemCarrinhoController.save(novoItemCarrinho);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testSaveNullItem() {
        ResponseEntity<String> response = this.itemCarrinhoController.save(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //@Test
    //void testSaveInvalidItem() {
      //  ItemCarrinho itemCarrinhoInvalido = new ItemCarrinho();

      //  assertThrows(Exception.class, () -> {
       //     this.itemCarrinhoController.save(itemCarrinhoInvalido);
       // }); }

    // LIST ALL

    @Test
    void testListAll() {
        ResponseEntity<List<ItemCarrinho>> responseEntity = this.itemCarrinhoController.listAll();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    // FIND BY ID

    @Test
    void testFindByIdOk() {
        ResponseEntity<ItemCarrinho> response = this.itemCarrinhoController.findById(1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(45, response.getBody().getValorUnitario());
    }

    @Test
    void testFindByIdNotFound() {
        ResponseEntity<ItemCarrinho> response = this.itemCarrinhoController.findById(100L);

        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
    }
	
	// UPDATE
	
	@Test
	void testUpdate() {
	    ItemCarrinho itemCarrinhoExistente = new ItemCarrinho(6, 32, 4, null, null);
	    ItemCarrinho itemCarrinhoAtualizado = new ItemCarrinho(7, 23, 5, null, null);
	    long idItemCarrinho = 1L;
	
	    // When
	    ResponseEntity<String> response = itemCarrinhoController.update(itemCarrinhoAtualizado, idItemCarrinho);
	
	    // Then
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testUpdateNotFound() {
	    // Given
	    ItemCarrinho itemCarrinhoAtualizado = new ItemCarrinho(7, 23, 5, null, null);
	    long idItemCarrinho = 100L;
	
	    // When
	    ResponseEntity<String> response = itemCarrinhoController.update(itemCarrinhoAtualizado, idItemCarrinho);
	
	    // Then
	    assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
	}
	
	// DELETE
	
	@Test
	void testDelete() {
	    // Given
	    long idItemCarrinho = 1L;
	
	    // When
	    ResponseEntity<String> response = itemCarrinhoController.delete(idItemCarrinho);
	
	    // Then
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    verify(itemCarrinhoRepository).deleteById(idItemCarrinho);
	}
	
	}
	
