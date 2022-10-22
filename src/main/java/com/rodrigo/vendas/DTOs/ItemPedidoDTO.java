package com.rodrigo.vendas.DTOs;

import java.math.BigDecimal;

import com.rodrigo.vendas.domain.ItemPedido;

public class ItemPedidoDTO {

	private String produto;
	private BigDecimal precoUnitario;
	private Integer quantidade;

	public ItemPedidoDTO() {

	}

	public ItemPedidoDTO(ItemPedido itemPedido) {

		this.produto = itemPedido.getProduto().getDescricaoProduto();
		this.precoUnitario = itemPedido.getProduto().getPrecoUnitario();
		this.quantidade = itemPedido.getQuantidade();

	}

	public double subTotal() {
		return precoUnitario.doubleValue() * quantidade;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

}
