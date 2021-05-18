package br.com.unip.tcc.vo;

import java.io.Serializable;

public class VendaVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer situacao;
	private Integer numero;
	private Integer idCliente;
	public Integer getSituacao() {
		return situacao;
	}
	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
}
