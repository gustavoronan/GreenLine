package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

    public List<Fornecedor> findByNomeFornecedor(String nomeFornecedor);

}