package com.gft.desafiomvc.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Vacina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Nome nao pode ser vazio.")
	private String nome;
	@NotEmpty(message = "Laboratorio nao pode ser vazio.")
	private String laboratorio;
	private Boolean isDose; // true = dose unica - False = 2 duas doses 
	@NotNull(message = "Intervalo nao pode ser vazio")
	private Long intervalo;
	
	@OneToMany(mappedBy = "vacina")
	private List<Lote> lotesvacinas;
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public Boolean getIsDose() {
		return isDose;
	}
	public Long getIntervalo() {
		return intervalo;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	public void setIsDose(Boolean isDose) {
		this.isDose = isDose;
	}
	public void setIntervalo(Long intervalo) {
		this.intervalo = intervalo;
	}
	
	
}
