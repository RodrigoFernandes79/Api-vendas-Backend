package com.rodrigo.vendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rodrigo.vendas.services.security.UsuarioService;

@EnableWebSecurity
public class SegurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
//método para encriptografar a senha
			return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Configurar a autenticação:
		auth
		.userDetailsService(usuarioService)
		.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Configurar a autorização(Pegar se o usuario que foi autenticado pelo método acima e verificar se 
		//ele tem autorização para uma determinada pagina.Quem tem acesso ao quê
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/api/clientes/**")
		.hasAnyRole("USER","ADMIN")
		.antMatchers("/api/pedidos/**")
		.hasAnyRole("USER","ADMIN")
		.antMatchers("/api/produtos/**")
		.hasRole("ADMIN")
		.and()
		.httpBasic();
	}

	
}
