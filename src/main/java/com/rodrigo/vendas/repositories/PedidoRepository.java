package com.rodrigo.vendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.vendas.domain.Cliente;
import com.rodrigo.vendas.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByCliente(Cliente cliente);
	
	

}
