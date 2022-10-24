package com.rodrigo.vendas.DTOs;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.rodrigo.vendas.domain.Pedido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PedidoDTO {

	private Integer id;
	private String clienteNome;
	private LocalDate dataPedido;
	private String status;
	private List<ItemPedidoDTO> itemPedidos;
	private Double total;

	

	public PedidoDTO(Pedido pedido) {

		this.id = pedido.getId();
		this.setClienteNome(pedido.getCliente().getNome());
		this.itemPedidos = pedido.getItemPedidos().stream().map(ItemPedidoDTO::new).collect(Collectors.toList());
		this.dataPedido = pedido.getDataPedido();
		this.status = pedido.getStatus().getDescricao();
		this.total = somaTotal();

	}

	public double somaTotal() {
		double soma = 0.0;
		for (ItemPedidoDTO x : itemPedidos) {

			soma += x.subTotal();
		}
		return soma;
	}
	
	
	
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDTO other = (PedidoDTO) obj;
		return Objects.equals(id, other.id);
	}

	
}
