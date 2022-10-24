package com.rodrigo.vendas.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;






@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusPedido {
	
	
	REALIZADO("REALIZADO") , 
	CANCELADO("CANCELADO") ;
	
	
	
	
	private String descricao;

	


	public static StatusPedido toEnum(String descricao) {
		if(descricao == null){
		return null;
	}
		for(StatusPedido x : StatusPedido.values()) {
			if(descricao.contains(x.getDescricao())) {
				return x;
			}
		}
			throw new IllegalArgumentException("Código "+descricao+ " não informada!");
		}




	
	

	}

	


