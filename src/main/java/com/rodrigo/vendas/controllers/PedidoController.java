package com.rodrigo.vendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.vendas.DTOs.PedidoDTO;
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

}
