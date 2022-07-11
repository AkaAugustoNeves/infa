package br.com.pilares.infaauthorizationserver.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.pilares.infaauthorizationserver.model.Perfil;

public class MilitarDTO implements UserDetails{
	
	private Long id;
	private String telefone;
	private String senha;
	private boolean isAtivo;
	private PatenteDTO patente;
	private SecaoDTO secao;
	private List<Perfil> perfis = new ArrayList<>();
	
	public MilitarDTO() {
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}
	@Override
	public String getPassword() {
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.telefone;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return this.isAtivo;
	}
	
	public void convertInPerfil(){
		perfis.add(new Perfil(convertSecaoPerfil(secao.getNome())));
		perfis.add(new Perfil(convertPatentPerfil(patente.getNome())));
		//return perfis;
	}
	
	private static String convertPatentPerfil(String patenteNome) {
		return "P_"+patenteNome.toUpperCase();
	}
	
	private static String convertSecaoPerfil(String secaoNome) {
		return "S_"+secaoNome.toUpperCase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public PatenteDTO getPatente() {
		return patente;
	}

	public void setPatente(PatenteDTO patente) {
		this.patente = patente;
	}

	public SecaoDTO getSecao() {
		return secao;
	}

	public void setSecao(SecaoDTO secao) {
		this.secao = secao;
	}

	public List<Perfil> getPerfis() {
		convertInPerfil();
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
}
