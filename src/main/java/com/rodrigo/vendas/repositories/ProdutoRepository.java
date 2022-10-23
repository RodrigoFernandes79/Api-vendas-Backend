package com.rodrigo.vendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.vendas.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Produto findByDescricaoProduto(String descricaoProduto);

	List<Produto> findByDescricaoProdutoIgnoreCaseContaining(String descricaoProduto);
	
	

}
