package com.rodrigo.vendas.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO {
//classe para passar as credenciais e depois de passar ele nos retorna o tokenDTO
	private String login;
	private String senha;
}
