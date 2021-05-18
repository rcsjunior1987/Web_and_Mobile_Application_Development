package br.com.unip.tcc.vo;

import java.io.Serializable;
import java.util.Calendar;

public class ClienteVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private Calendar dataNascimentoInicial;
	private Calendar dataNascimentoFinal;
	private String rg;
	private String cpf;
	private String sexo;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataNascimentoInicial() {
		return dataNascimentoInicial;
	}
	public void setDataNascimentoInicial(Calendar dataNascimentoInicial) {
		this.dataNascimentoInicial = dataNascimentoInicial;
	}
	public Calendar getDataNascimentoFinal() {
		return dataNascimentoFinal;
	}
	public void setDataNascimentoFinal(Calendar dataNascimentoFinal) {
		this.dataNascimentoFinal = dataNascimentoFinal;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}
