package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
