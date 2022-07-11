package br.com.pilares.infaauthorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.pilares.infaauthorizationserver.feignclients.MilitarFeignClient;
import br.com.pilares.infaauthorizationserver.filter.AutenticacaoViaTokenFilter;
import br.com.pilares.infaauthorizationserver.service.LoginService;
import br.com.pilares.infaauthorizationserver.service.TokenService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecutiryConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginService ls;
	
	@Autowired
	private TokenService ts;
	
	@Autowired
	private MilitarFeignClient ufc;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth/**").permitAll()
		.antMatchers(HttpMethod.POST, "/teste/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(ts, ufc), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(ls).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
