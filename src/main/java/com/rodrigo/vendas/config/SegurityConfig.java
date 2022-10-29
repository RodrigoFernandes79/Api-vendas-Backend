package com.rodrigo.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SegurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
//método para encriptografar a senha
			return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Configurar a autenticação:
		auth.inMemoryAuthentication()
		.passwordEncoder(passwordEncoder())
		.withUser("Rodrigo")
		.password(passwordEncoder().encode("123"))
		.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Configurar a autorização(Pegar se o usuario que foi autenticado pelo método acima e verificar se 
		//ele tem autorização para uma determinada pagina.Quem tem acesso ao quê
		super.configure(http);
	}

	
}
