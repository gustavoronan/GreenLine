package app.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<UsuarioAuth, Long>{

	public Optional<UsuarioAuth> findByUsername(String login);
	
}
