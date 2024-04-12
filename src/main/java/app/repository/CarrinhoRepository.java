package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

}
