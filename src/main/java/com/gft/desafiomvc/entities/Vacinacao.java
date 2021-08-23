package com.gft.desafiomvc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Vacinacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Pessoa pessoa;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataVacinacao;
	
	@ManyToOne
	private Lote lote;
	
	@ManyToOne
	private Local local;
	
	@NotEmpty(message = "Dose nao pode ser vazio.")
	private String dose;
	
	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataVacinacao() {
		return dataVacinacao;
	}

	public void setDataVacinacao(Date dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}
}
