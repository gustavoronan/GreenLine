package app.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmailUsuario (String emailUsuario);
	
	//public List<Usuario> findByClienteNomeCliente (String nomeCliente);

}
