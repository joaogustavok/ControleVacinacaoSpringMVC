package com.gft.desafiomvc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Lote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Vacina vacina;
	
	@Digits(fraction = 0, integer = 100)
	private Integer qntRecebida;
	
	@NotEmpty(message = "Indentificação não pode ser vazio.")
	private String identificacao;
	
	private Integer qntRestante;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataRecebimento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	Date dataValidade;
	
	public Long getId() {
		return id;
	}
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Vacina getVacina() {
		return vacina;
	}
	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}
	public Integer getQntRecebida() {
		return qntRecebida;
	}
	public void setQntRecebida(Integer qntRecebida) {
		this.qntRecebida = qntRecebida;
	}
	public Integer getQntRestante() {
		return qntRestante;
	}
	public void setQntRestante(Integer qntRestante) {
		this.qntRestante = qntRestante;
	}
	public Date getDataRecebimento() {
		return dataRecebimento;
	}
	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	
	
	public String getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
}
