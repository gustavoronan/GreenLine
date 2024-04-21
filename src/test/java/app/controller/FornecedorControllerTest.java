package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import app.entity.Fornecedor;
import app.repository.FornecedorRepository;
import app.service.FornecedorService;

@SpringBootTest
public class FornecedorControllerTest {

	@Autowired
    FornecedorController fornecedorController;
   
    @MockBean
    FornecedorRepository fornecedorRepository;
    FornecedorService fornecedorService;
    

    
    @BeforeEach
    void setup () {
        List<Fornecedor> listFornecedor = new ArrayList<>();
        listFornecedor.add(new Fornecedor(1, "643271", "Gabriel", "efdsaf@gmail.com", null));
        listFornecedor.add(new Fornecedor(2, "334525", "Gustavo", "dsafasadasd@gmail.com", null));
        when(this.fornecedorRepository.findAll()).thenReturn(listFornecedor);
        
      //MOCK DE SAVE
        Fornecedor novoFornecedor = new Fornecedor(3, "234525", "Lucas", "cdefgdi@gmail.com", null);
        when(this.fornecedorRepository.save(novoFornecedor)).thenReturn(novoFornecedor);

        //MOCK DE FINDBYID
        Fornecedor objetoASerRetornado = new Fornecedor(4, "12345678", "Joao", "abcdefghi@gmail.com", null);
        when(this.fornecedorRepository.findById(1L)).thenReturn(Optional.of(objetoASerRetornado));

        }

    	//SAVE

        @Test
        void testSave() {
            Fornecedor novoFornecedor = new Fornecedor (3, "234525", "Lucas", "cdefgdi@gmail.com", null);


            ResponseEntity<String> response = this.fornecedorController.save(novoFornecedor);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
           // assertEquals(" Funcionario salvo com sucesso!!", response.getBody());
        }
        
        @Test
    	void testSaveNOTOK() {
    		 Fornecedor fornecedorInvalido = new Fornecedor();
    		 fornecedorInvalido.setNomeFornecedor(null);
    		 
    	    ResponseEntity<String> response = this.fornecedorController.save(null);

    	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    	}
        
        @Test
        void testSaveNOTOK2() {
        	
            Fornecedor novoFornecedor = new Fornecedor (3, "", "Sei la", "dasdas@hotmail", null);
            
            //ResponseEntity<String> response = this.fornecedorController.save(novoFornecedor);

            //assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            
            assertThrows(Exception.class, () -> {
            	this.fornecedorController.save(novoFornecedor);
            });

        }

        //READ
        
        @Test
        void testListAll() {

            ResponseEntity<List<Fornecedor>> listFornecedor = this.fornecedorController.listAll();
            assertEquals(2, listFornecedor.getBody().size());    
        }

        
        @Test
        void testFindByIdOk() {

            ResponseEntity<Fornecedor> response = this.fornecedorController.findById(1L);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Joao", response.getBody().getNomeFornecedor());

        }

        @Test
        void testFindByIdNOTOK() {

            ResponseEntity<Fornecedor> response = this.fornecedorController.findById(-1L);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        }

        @Test
        void testFindByIdOkSemDado() {

            ResponseEntity<Fornecedor> response = this.fornecedorController.findById(22222L);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        }
        
        @Test
        void testFindByNomeFornecedor_ValidParameter() {
            String nomeFornecedor = "Gabriel"; 

            List<Fornecedor> fornecedores = new ArrayList<>();
            fornecedores.add(new Fornecedor(1, "643271", "Gabriel", "efdsaf@gmail.com", null));

            when(this.fornecedorRepository.findByNomeFornecedor(nomeFornecedor)).thenReturn(fornecedores);

            ResponseEntity<List<Fornecedor>> responseEntity = fornecedorController.findByNomeFornecedor(nomeFornecedor);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());        
        }
        
        
        //UPDATE
        
        @Test
    	void testUpdate() {
    		
    		Fornecedor fornecedorExistente = new Fornecedor(6, "334525", "Gustavo", "dsafasadasd@gmail.com", null);
    		Fornecedor fornecedorAtualizada = new Fornecedor(7, "23543", "Gabriel", "gasolina@gmail.com", null);
    	    long idFornecedor = 1L;
    		
    		ResponseEntity<String> response = fornecedorController.update(fornecedorAtualizada, idFornecedor);
    	    
    		assertEquals(HttpStatus.OK, response.getStatusCode());
    	   
    	    when(fornecedorRepository.findById(idFornecedor)).thenReturn(Optional.of(fornecedorExistente));
    	    when(fornecedorRepository.save(fornecedorAtualizada)).thenReturn(fornecedorAtualizada);
    	}
        
     

        //DELETE
        
        @Test
        void testDelete() {
            long idFornecedor= 1L;
            ResponseEntity<String> response = fornecedorController.delete(idFornecedor);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            verify(fornecedorRepository).deleteById(idFornecedor);
        }
      
}
