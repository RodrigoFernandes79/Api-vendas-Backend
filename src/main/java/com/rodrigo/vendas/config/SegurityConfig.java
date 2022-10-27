package com.rodrigo.vendas.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SegurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Configurar a autenticação:
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Configurar a autorização(Pegar se o usuario que foi autenticado pelo método acima e verificar se 
		//ele tem autorização para uma determinada pagina.Quem tem acesso ao quê
		super.configure(http);
	}

	
}
