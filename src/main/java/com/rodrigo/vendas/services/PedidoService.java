package com.rodrigo.vendas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.vendas.DTOs.PedidoDTO;
import com.rodrigo.vendas.domain.Pedido;
import com.rodrigo.vendas.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<PedidoDTO> listarPedidos() {
		List<Pedido> obj = pedidoRepository.findAll();

		List<PedidoDTO> objDto = obj.stream().map(objList -> new PedidoDTO(objList)).collect(Collectors.toList());

		return objDto;
	}

}
