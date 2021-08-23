package com.gft.desafiomvc.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {
	@NotEmpty(message = "CEP nao pode ser vazio.")
	@Size(min = 0, max = 8)
	private String cep;
	@NotEmpty(message = "Logradouro nao pode ser vazio.")
	private String logradouro;
	@NotNull(message = "Numero nao pode ser vazio")
	private Integer numero;
	@NotEmpty(message = "Complemento nao pode ser vazio.")
	private String complemento;
	@NotEmpty(message = "Municipio nao pode ser vazio.")
	private String municipio;
	@NotEmpty(message = "Estado nao pode ser vazio.")
	private String estado;
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
