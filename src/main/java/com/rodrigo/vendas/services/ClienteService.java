package com.rodrigo.vendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rodrigo.vendas.domain.Cliente;
import com.rodrigo.vendas.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	
	public Cliente listarClientePorId(Integer id) {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
				
		
		return obj
				.orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
		
	}


	public void deletarCliente(Integer id) {
		try {
		Cliente obj = clienteRepository.findById(id)
				.orElseThrow(() -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		
		clienteRepository.delete(obj);
 		
	} catch(DataIntegrityViolationException e) {
		throw new DataIntegrityViolationException(
				"Cliente não pode ser deletado.Possui pedidos associados");
	}
}


	public Cliente atualizarCliente(Integer id, Cliente cliente) {
		
		return clienteRepository.findById(id)
				.map(cli ->{
					cli.getId();
					cli.setNome(cliente.getNome());
					
					Cliente obj = clienteRepository.save(cli);
					return obj;
				}).orElseThrow(()->
					new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado!"));
				
	}
	}
