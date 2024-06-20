package app.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/usuario")
@RestController
@CrossOrigin("*")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/save") //ok
	public ResponseEntity<String> save(@RequestBody Usuario usuario){

		try {
			
			String mensagem = this.usuarioService.save(usuario);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);	
			
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}

	@GetMapping("/listAll") //ok
	public ResponseEntity <List<Usuario>> listAll(){

		try {
			List<Usuario> lista = this.usuarioService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {			
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}

	@GetMapping("/findById/{idUsuario}") //ok
	public ResponseEntity <Usuario> findById(@PathVariable long idUsuario){

		try {
			Usuario usuario = this.usuarioService.findById(idUsuario);
			return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		} catch (Exception e) {			
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
	
	@PutMapping("/update/{idUsuario}") //ok
	public ResponseEntity<String> update(@RequestBody Usuario usuario, @PathVariable long idUsuario){

		try {
			String mensagem = this.usuarioService.update(usuario, idUsuario);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);		
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
	
	@DeleteMapping("/delete/{idUsuario}")//ok
	public ResponseEntity<String> delete(@PathVariable long idUsuario){

		try {
			String mensagem = this.usuarioService.delete(idUsuario);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);		
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
	@GetMapping("/findByEmail")
	public ResponseEntity <Optional<Usuario>> findByEmail(@RequestParam String emailUsuario) { //ok
		try {
			Optional <Usuario> usuarioOpt = this.usuarioService.findByEmail(emailUsuario);
			if(usuarioOpt.isPresent()) {
                return new ResponseEntity<>(usuarioOpt, HttpStatus.OK);
			}
			else {
                return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
            }
		} catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByClienteNome")
	public ResponseEntity<List<Usuario>> findByClienteNome(@RequestParam String clienteNome) {
		try {
			List<Usuario> lista = this.usuarioService.findByClienteNome(clienteNome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	

	   @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody Autenticador autenticador) {
		   try {
				return ResponseEntity.ok(usuarioService.login(autenticador));
			} catch (Exception e) {	
				return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

			}
	    }
    

	@GetMapping("/findByLoginId/{idUsuario}") //ok
	public ResponseEntity <Usuario> findByLoginId(@PathVariable long idUsuario){

		try {
			Usuario usuario = this.usuarioService.findById(idUsuario);
			return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		} catch (Exception e) {			
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
}
