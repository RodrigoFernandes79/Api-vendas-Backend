package com.rodrigo.vendas.DTOs;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.rodrigo.vendas.domain.Pedido;

public class PedidoDTO {

	private Integer id;
	private String clienteNome;
	private LocalDate dataPedido;
	private List<ItemPedidoDTO> itemPedidos;
	private Double total;

	public PedidoDTO() {

	}

	public PedidoDTO(Pedido pedido) {

		this.id = pedido.getId();
		this.setClienteNome(pedido.getCliente().getNome());
		this.itemPedidos = pedido.getItemPedidos().stream().map(ItemPedidoDTO::new).collect(Collectors.toList());
		this.dataPedido = pedido.getDataPedido();
		this.setTotal(somaTotal());

	}

	public double somaTotal() {
		double soma = 0.0;
		for (ItemPedidoDTO x : itemPedidos) {

			soma += x.subTotal();
		}
		return soma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<ItemPedidoDTO> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(List<ItemPedidoDTO> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
