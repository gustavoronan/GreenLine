package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

}
