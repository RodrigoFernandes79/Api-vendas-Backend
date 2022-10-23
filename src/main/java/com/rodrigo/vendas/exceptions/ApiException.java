package com.rodrigo.vendas.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ApiException {

	private LocalDateTime timestamp;
	private String mensagem;
	private Integer status;
	private String path;
}
