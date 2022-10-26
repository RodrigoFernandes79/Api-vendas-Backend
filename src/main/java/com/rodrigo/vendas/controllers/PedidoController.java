package com.rodrigo.vendas.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.vendas.DTOs.PedidoDTO;
import com.rodrigo.vendas.domain.Pedido;
import com.rodrigo.vendas.services.PedidoService;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> listarPedidos() {

		List<PedidoDTO> objDTO = pedidoService.listarPedidos();

		return ResponseEntity.ok().body(objDTO);
	}

	@PostMapping
	public ResponseEntity<PedidoDTO> inserirPedido(@RequestBody Pedido pedido) {
		
		
		PedidoDTO objDto = pedidoService.inserirPedido(pedido);

		return ResponseEntity.status(HttpStatus.CREATED).body(objDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> listarPedidoPorId(@PathVariable Integer id) {

		Pedido objDto = pedidoService.listarPedidoPorId(id);
		return ResponseEntity.ok().body(objDto);

	}

	@Transactional
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarStatusPedido(@PathVariable Integer id, @RequestBody String status) {

		pedidoService.atualizarStatusPedido(id, status);

	}
}