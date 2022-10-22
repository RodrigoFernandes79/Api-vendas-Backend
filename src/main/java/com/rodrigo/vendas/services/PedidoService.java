package com.rodrigo.vendas.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.vendas.DTOs.PedidoDTO;
import com.rodrigo.vendas.domain.ItemPedido;
import com.rodrigo.vendas.domain.Pedido;
import com.rodrigo.vendas.repositories.ItemPedidoRepository;
import com.rodrigo.vendas.repositories.PedidoRepository;

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

	public Pedido inserirPedido(Pedido pedido) {
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(clienteService.listarClientePorId(pedido.getCliente().getId()));
		
		pedido = pedidoRepository.save(pedido);
		for(ItemPedido itemPedidos : pedido.getItemPedidos()) {
			
			itemPedidos.setProduto(produtoService.listarProdutoPorId(itemPedidos.getProduto().getId()));
			itemPedidos.setQuantidade(itemPedidos.getQuantidade());
			itemPedidos.setSubTotal(itemPedidos.subTotal());
			itemPedidos.setPedido(pedido);
			
		}
	
		itemRepository.saveAll(pedido.getItemPedidos());
		
		
		
		return pedido;
	}

}
