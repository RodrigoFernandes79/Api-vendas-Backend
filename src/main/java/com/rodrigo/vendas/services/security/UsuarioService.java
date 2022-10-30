package com.rodrigo.vendas.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//Classe que carrega o usuario na base de dados através do login
@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(!username.equals("Rodrigo")) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		return User
				.builder()
				.username("Rodrigo")
				.password(encoder.encode("123"))
				.roles("USER","ADMIN")
				.build();
	}

}
