package com.rodrigo.vendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigo.vendas.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	//consulta spql:
	
	
	@Query(value="SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
	List<Cliente> encontrarPorNome(@Param(value="nome") String nome);

//	//consulta com sql nativo:
//	@Query(value="SELECT * FROM Cliente  WHERE nome LIKE '%:nome%' ", nativeQuery = true)
//	List<Cliente> encontrarPorNomeSql(@Param(value="nome") String nome);
	
	@Query(value="SELECT c FROM Cliente c left join fetch c.pedidos where c.id =:id ")
	Cliente encontrarClienteComPedidos(@Param(value="id")Integer id);
	
	
	Cliente findBycpf(String cpf);

}
