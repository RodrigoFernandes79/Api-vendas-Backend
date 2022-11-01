package com.rodrigo.vendas.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.vendas.domain.Usuario;
import com.rodrigo.vendas.services.security.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	
	private final UsuarioService usuarioService;
	
	
	private final PasswordEncoder encoder;
	
	@PostMapping
	public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody Usuario usuario){
		
		String senhaBcryptografada = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaBcryptografada);
		Usuario obj = usuarioService.salvarUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
		
	}

}
