package com.rodrigo.vendas.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.vendas.DTOs.PedidoDTO;
import com.rodrigo.vendas.domain.ItemPedido;
import com.rodrigo.vendas.domain.Pedido;
import com.rodrigo.vendas.domain.enums.StatusPedido;
import com.rodrigo.vendas.repositories.ItemPedidoRepository;
import com.rodrigo.vendas.repositories.PedidoRepository;
import com.rodrigo.vendas.services.exceptions.EntityNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemRepository;
	
	

	public List<PedidoDTO> listarPedidos() {

		List<Pedido> obj = pedidoRepository.findAll();

		List<PedidoDTO> objDto = obj.stream().map(objList -> new PedidoDTO(objList)).collect(Collectors.toList());

		return objDto;
	}

	public PedidoDTO inserirPedido(Pedido pedido) {
		
		
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(clienteService.listarClientePorId(pedido.getCliente().getId()));
		pedido.setStatus(StatusPedido.REALIZADO.getDescricao());
		pedido = pedidoRepository.save(pedido);
		
		for (ItemPedido itemPedidos : pedido.getItemPedidos()) {

			itemPedidos.setProduto(produtoService.listarProdutoPorId(itemPedidos.getProduto().getId()));
			itemPedidos.setQuantidade(itemPedidos.getQuantidade());
			itemPedidos.subTotal();
		
			
		}
		
		
		itemRepository.saveAll(pedido.getItemPedidos());
		PedidoDTO pedidoDto = new PedidoDTO(pedido);
		
		return pedidoDto;
	
		}
	public Pedido listarPedidoPorId(Integer id) {

		Pedido obj = pedidoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Id " + id + " não Encontrado"));

		return obj;
	}

	public void atualizarStatusPedido(Integer id, String status) {
		pedidoRepository.findById(id).map(obj -> {
			obj.setStatus(status);
			Pedido ped = pedidoRepository.save(obj);
			return ped;
		}).orElseThrow(() -> new EntityNotFoundException("ID " + id + " não encontrado!"));

	}
	public  PedidoDTO convertToDTO(Pedido pedido) {
		
		PedidoDTO pedDto = new PedidoDTO(pedido);
				
				return pedDto;
				
				

}

}