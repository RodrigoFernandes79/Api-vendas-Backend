package com.rodrigo.vendas.DTOs;

import java.util.Objects;

import com.rodrigo.vendas.domain.ItemPedido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemPedidoDTO {

	private String produto;
	private Double precoUnitario;
	private Integer quantidade;
	
	

	public ItemPedidoDTO(ItemPedido itemPedido) {

		this.produto = itemPedido.getProduto().getDescricaoProduto();
		this.precoUnitario = itemPedido.getProduto().getPrecoUnitario();
		this.quantidade = itemPedido.getQuantidade();
		

	}

	public double subTotal() {
		return precoUnitario * quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(precoUnitario, produto, quantidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoDTO other = (ItemPedidoDTO) obj;
		return Objects.equals(precoUnitario, other.precoUnitario) && Objects.equals(produto, other.produto)
				&& Objects.equals(quantidade, other.quantidade);
	}

		
}
