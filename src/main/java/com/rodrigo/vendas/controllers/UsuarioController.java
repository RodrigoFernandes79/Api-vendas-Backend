package com.rodrigo.vendas.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rodrigo.vendas.DTOs.CredenciaisDTO;
import com.rodrigo.vendas.DTOs.TokenDTO;
import com.rodrigo.vendas.domain.Usuario;
import com.rodrigo.vendas.services.exceptions.SenhaInvalidaException;
import com.rodrigo.vendas.services.exceptions.UsernameNotFoundException;
import com.rodrigo.vendas.services.security.UsuarioService;
import com.rodrigo.vendas.services.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	
	private final UsuarioService usuarioService;
	
	private final PasswordEncoder encoder;
	
	private final JwtService jwtService;
	
	@PostMapping
	public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody Usuario usuario){
		
		String senhaBcryptografada = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaBcryptografada);
		Usuario obj = usuarioService.salvarUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
		
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
		Usuario usuario = Usuario.builder()
			.login(credenciais.getLogin())
			.senha(credenciais.getSenha()).build();
		UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
		
		String token = jwtService.gerarToken(usuario);
		
		return new TokenDTO(usuario.getLogin(), token);
		
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
			
		}
		
		
	}

}
