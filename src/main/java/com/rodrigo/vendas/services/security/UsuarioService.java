package com.rodrigo.vendas.services.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodrigo.vendas.domain.Usuario;
import com.rodrigo.vendas.repositories.UsuarioRepository;
import com.rodrigo.vendas.services.exceptions.SenhaInvalidaException;

//Classe que carrega o usuario na base de dados através do login
@Service
public class UsuarioService implements UserDetailsService{
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private  PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(username)
				.orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado na base de dados."));
		
		String[] roles = usuario.isAdmin() ? new String[]{"ADMIN","USER"} : new String[]{"USER"};
		
		return User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}
	@Transactional
	public Usuario salvarUsuario( Usuario usuario) {
		Usuario obj = usuarioRepository.save(usuario);
		
		return obj;
	}
	
	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean senhasEstaoBatendo = encoder.matches(usuario.getSenha(), user.getPassword());
		if(senhasEstaoBatendo) {
			return user;
		}else {
			throw new SenhaInvalidaException();
		}
	}

}
