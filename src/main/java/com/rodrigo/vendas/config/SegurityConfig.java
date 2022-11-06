package com.rodrigo.vendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rodrigo.vendas.services.security.UsuarioService;
import com.rodrigo.vendas.services.security.jwt.JwtAuthFilter;
import com.rodrigo.vendas.services.security.jwt.JwtService;


@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SegurityConfig extends WebSecurityConfigurerAdapter{
	@Lazy
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	
	
	@Bean
	public  PasswordEncoder passwordEncoder() {
//método para encriptografar a senha
			return new BCryptPasswordEncoder();
	}
	
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService,usuarioService);
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
		.antMatchers(HttpMethod.POST, "/api/usuarios/**")
        .permitAll()
        .anyRequest().authenticated()
        .and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	  @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers(
	                "/v2/api-docs",
	                "/configuration/ui",
	                "/swagger-resources/**",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/webjars/**");
	    }

	
}
