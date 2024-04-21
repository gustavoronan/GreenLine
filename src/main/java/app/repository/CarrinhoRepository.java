package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
	
    public List<Carrinho> findByItemCarrinhoProdutoNomeProduto(String nomeProduto);
	
	@Query("FROM Carrinho c WHERE c.valorCarrinho >= :valorCarrinho")
	public List<Carrinho> buscarVendaAcimaValor(double valorCarrinho);
	
	@Query("FROM Carrinho c WHERE c.valorCarrinho <= :valorCarrinho")
	public List<Carrinho> buscarVendaAbaixoValor(double valorCarrinho);
	
}
