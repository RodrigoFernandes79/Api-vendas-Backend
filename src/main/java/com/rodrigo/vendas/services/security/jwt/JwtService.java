package com.rodrigo.vendas.services.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rodrigo.vendas.domain.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	@Value("${security.jwt.chave-de-assinatura}")
	private String chaveDeAssinatura;

	// método para gerar o Token
	public String gerarToken(Usuario usuario) {
		long expiraToken = Long.valueOf(expiracao);
		LocalDateTime DataHoraExpiracao = LocalDateTime.now().plusMinutes(expiraToken);
		Instant instante = DataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instante);
		return Jwts.builder().setSubject(usuario.getLogin()).setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveDeAssinatura).compact();
	}

	//método para decodificar o token
	Claims obterClaims(String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(chaveDeAssinatura)
				.parseClaimsJws(token)
				.getBody();

	}

	// método para saber se o token é válido
	public boolean tokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao
									.toInstant()
									.atZone(ZoneId.systemDefault()).toLocalDateTime();

			return !LocalDateTime.now().isAfter(data); // o token é valido quando a data-hora atual
														// não é depois da data hora da expiração do token
		} catch (Exception e) {
			return false;

		}
	}

	// método para saber quem é o usuário que vai estar logado e que mandou o token
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return obterClaims(token).getSubject();
	}

}
