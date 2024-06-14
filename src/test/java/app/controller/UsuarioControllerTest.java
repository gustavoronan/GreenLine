package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
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

import app.entity.Cliente;
import app.entity.Usuario;
import app.repository.UsuarioRepository;
import app.service.UsuarioService;

@SpringBootTest

public class UsuarioControllerTest {



	@Autowired

	UsuarioController UsuarioController;

	@MockBean

	UsuarioRepository usuarioRepository;

	UsuarioService usuarioService;



	@BeforeEach

	void setup (){

		List<Usuario> lista = new ArrayList<>();

		lista.add(new Usuario(1, "Ricardo@.com", "333", null));

		lista.add(new Usuario(2, "Gustavo@.com", "senha", null));

		Usuario usuario = new Usuario(3, "Josericardo@.com", "2444", null);
		//Cliente cliente = new cliente(4,)



		when(this.usuarioRepository.findAll()).thenReturn(lista);

		when(this.usuarioRepository.save(usuario)).thenReturn(usuario);

		when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

	}



	@Test

	@DisplayName("findall usuario")

	void cenario1() {

		ResponseEntity<List<Usuario>> response = this.UsuarioController.listAll(); // chama o metodo listall

		List<Usuario> list = response.getBody(); //pega o corpo do metodo

		assertEquals(2, list.size()); //espera uma lista de dois elementos salvos

		assertEquals("Ricardo@.com", list.get(0).getEmailUsuario()); // espera um retorno com este email

	}

	@Test
	@DisplayName("Save Usuario")
	void cenario2() {
		Usuario usuario = new Usuario(3, "Josericardo@.com", "2444", null);

		ResponseEntity<String> response = this.UsuarioController.save(usuario);

		assertEquals(usuario, usuario);
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	@DisplayName("SAVE MOCK EXCEPTION")
	void cenario3() {

		Usuario usuario = null;
		ResponseEntity<String> response = this.UsuarioController.save(usuario);

		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
	}


	@Test
	@DisplayName("FINDBYID MOCK EXCEPTION")
	void cenario4() {

		ResponseEntity<Usuario> response = this.UsuarioController.findById(9L);

		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());

	}

	@Test
	@DisplayName("FINDBYEMAIl")
	void cenario5() {

		List<Usuario> lista = new ArrayList<>();

		lista.add(new Usuario(1, "Ricardo@.com", "333", null));

		lista.add(new Usuario(2, "Gustavo@.com", "senha", null));

		ResponseEntity<List<Usuario>> response = this.UsuarioController.findByEmail("Ricardo@.com");

		assertEquals(HttpStatus.OK, response.getStatusCode());


	}

	@Test
	@DisplayName("FINDBYNOMECLIENTE")
	void cenario6() {

		List<Cliente> lista = new ArrayList<>();

		// Criar clientes para adicionar à lista
		Cliente cliente1 = new Cliente();
		cliente1.setIdCliente(1);
		cliente1.setNomeCliente("Ricardo");
		cliente1.setTelefoneCliente("333");
		cliente1.setEnderecoCliente("Endereço do Ricardo");

		Cliente cliente2 = new Cliente();
		cliente2.setIdCliente(2);
		cliente2.setNomeCliente("Gustavo");
		cliente2.setTelefoneCliente("senha");
		cliente2.setEnderecoCliente("Endereço do Gustavo");

		lista.add(cliente1);
		lista.add(cliente2);

		// Simular a chamada do método findByClienteNome do UsuarioController
		ResponseEntity<List<Usuario>> response = UsuarioController.findByClienteNome("Ricardo");

		// Verificar se o status code do ResponseEntity é HttpStatus.OK
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
    void testDelete() {
        long idUsuario= 1L;
        ResponseEntity<String> response = UsuarioController.delete(idUsuario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usuarioRepository).deleteById(idUsuario);
    }
}
