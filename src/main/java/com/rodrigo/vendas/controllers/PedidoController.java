package com.rodrigo.vendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<Pedido> inserirPedido(@RequestBody Pedido pedido){
		Pedido obj = pedidoService.inserirPedido(pedido);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> listarPedidoPorId(@PathVariable Integer id){
		
		Pedido objDto = pedidoService.listarPedidoPorId(id);
		return ResponseEntity.ok().body(objDto);
		
	}
	
	
}