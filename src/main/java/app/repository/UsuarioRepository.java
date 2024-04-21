package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public List<Usuario> findByEmailUsuario (String emailUsuario);
	
	public List<Usuario> findByClienteNomeCliente (String nomeCliente);

}
