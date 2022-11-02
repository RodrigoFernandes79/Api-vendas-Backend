package com.rodrigo.vendas.services.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.rodrigo.vendas.VendasApplication;
import com.rodrigo.vendas.domain.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class JwtService {
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	@Value("${security.jwt.chave-de-assinatura}")
	private String chaveDeAssinatura;
	
	public String gerarToken(Usuario usuario) {
		long expiraToken = Long.valueOf(expiracao);
		LocalDateTime DataHoraExpiracao = LocalDateTime.now().plusMinutes(expiraToken);
		Instant instante = DataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data =  Date.from(instante);
		return Jwts
				.builder()
				.setSubject(usuario.getLogin())
				.setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveDeAssinatura)
				.compact();
	}
		

	


}
