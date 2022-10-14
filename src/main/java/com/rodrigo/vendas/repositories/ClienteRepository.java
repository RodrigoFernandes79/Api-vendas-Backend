package com.rodrigo.vendas.repositories;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigo.vendas.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	
	@Query(name="SELECT C FROM Cliente c WHERE c.nome LIKE :nome")
	List<Cliente> encontrarPorNome(@Param(value="nome") String nome);

}
