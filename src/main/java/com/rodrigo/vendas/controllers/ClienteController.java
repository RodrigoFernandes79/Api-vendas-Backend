package com.rodrigo.vendas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.vendas.domain.Cliente;
import com.rodrigo.vendas.repositories.ClienteRepository;
import com.rodrigo.vendas.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente){
		
		Cliente obj = clienteRepository.save(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarClientePorId(@PathVariable Integer id){
		
		Cliente obj = clienteService.listarClientePorId(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
}
