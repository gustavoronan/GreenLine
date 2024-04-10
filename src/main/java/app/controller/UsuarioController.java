package app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/usuario")
@RestController
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Usuario usuario){

		try {
			
			String mensagem = this.usuarioService.save(usuario);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);	
			
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}

	@GetMapping("/listAll")
	public ResponseEntity <List<Usuario>> listAll(){

		try {
			List<Usuario> lista = this.usuarioService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.CREATED);
		} catch (Exception e) {			
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}

	@GetMapping("/findById/{idUsuario}")
	public ResponseEntity <Usuario> findById(@PathVariable long idUsuario){

		try {
			Usuario usuario = this.usuarioService.findById(idUsuario);
			return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		} catch (Exception e) {			
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
	
	@PutMapping("/update/{idUsuario}")
	public ResponseEntity<String> update(@RequestBody Usuario usuario, @PathVariable long idUsuario){

		try {
			String mensagem = this.usuarioService.update(usuario, idUsuario);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);		
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
	
	@DeleteMapping("/delete/{idUsuario}")
	public ResponseEntity<String> update(@PathVariable long idUsuario){

		try {
			String mensagem = this.usuarioService.delete(idUsuario);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);		
		} catch (Exception e) {

			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);		

		}

	}
}
