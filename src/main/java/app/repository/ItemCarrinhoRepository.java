package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.ItemCarrinho;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

}
