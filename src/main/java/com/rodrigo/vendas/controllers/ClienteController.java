package com.rodrigo.vendas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.vendas.domain.Cliente;
import com.rodrigo.vendas.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody Cliente cliente) {

		Cliente obj = clienteService.salvar(cliente);

		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarClientePorId(@PathVariable Integer id) {

		Cliente obj = clienteService.listarClientePorId(id);

		return ResponseEntity.ok().body(obj);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Integer id) {

		clienteService.deletarCliente(id);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id,@Valid @RequestBody Cliente cliente) {

		Cliente obj = clienteService.atualizarCliente(id, cliente);

		return ResponseEntity.ok().body(obj);
	}

	@GetMapping // localhost:8080/api/clientes/?nome="nome"
	public ResponseEntity<Page<Cliente>> listarClientesPorNome(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Cliente> obj = clienteService.listarClientesPorNome(nome, page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(obj);

	}
}
