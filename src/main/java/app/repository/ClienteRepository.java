package app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.auth.Usuario;
import app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	
	public Optional<Cliente> findByUsuario(Usuario usuario);

}
