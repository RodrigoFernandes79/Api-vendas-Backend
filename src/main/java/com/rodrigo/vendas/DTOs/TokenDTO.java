package com.rodrigo.vendas.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
//classe para retornar o token após a autenticação das credenciais(CredenciaisDTO)
	
	private String login;
	private String token;
}
