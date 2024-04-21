package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Usuario;
import app.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public String save (Usuario usuario) {
		this.usuarioRepository.save(usuario);
		return usuario.getEmailUsuario() + " Foi registrado";
	}
	
	public List <Usuario> findAll () {
	return this.usuarioRepository.findAll();
		
	}
	
	
	
	public Usuario findById(long idUsuario) {

		Usuario usuario= this.usuarioRepository.findById(idUsuario).get();
		return usuario;

	}
	
	public String update (Usuario usuario, long idUsuario) {
		usuario.setIdUsuario(idUsuario);
		this.usuarioRepository.save(usuario);
		return "O " + usuario.getEmailUsuario() + " Foi atualizado";
		
	}
	
	public String delete (long idUsuario) {
		this.usuarioRepository.deleteById(idUsuario);
		return "Usuario deletado";
	}
	public List<Usuario> findByEmail(String emailUsuario){
		return this.usuarioRepository.findByEmailUsuario(emailUsuario);
	}
	public List<Usuario> findByClienteNome(String clienteNome){
		return this.usuarioRepository.findByClienteNomeCliente(clienteNome);
	}
}
