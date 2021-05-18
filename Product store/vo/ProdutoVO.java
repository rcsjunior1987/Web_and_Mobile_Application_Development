package br.com.unip.tcc.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	private String fornecedor;
	private String marca;
	private BigDecimal valorCompraInicial;
	private BigDecimal valorCompraFinal;
	private BigDecimal valorVendaInicial;
	private BigDecimal valorVendaFinal;
	private Integer estoque;
	public Integer getEstoque() {
		return estoque;
	}
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	public BigDecimal getValorCompraInicial() {
		return valorCompraInicial;
	}
	public void setValorCompraInicial(BigDecimal valorCompraInicial) {
		this.valorCompraInicial = valorCompraInicial;
	}
	public BigDecimal getValorCompraFinal() {
		return valorCompraFinal;
	}
	public void setValorCompraFinal(BigDecimal valorCompraFinal) {
		this.valorCompraFinal = valorCompraFinal;
	}
	public BigDecimal getValorVendaInicial() {
		return valorVendaInicial;
	}
	public void setValorVendaInicial(BigDecimal valorVendaInicial) {
		this.valorVendaInicial = valorVendaInicial;
	}
	public BigDecimal getValorVendaFinal() {
		return valorVendaFinal;
	}
	public void setValorVendaFinal(BigDecimal valorVendaFinal) {
		this.valorVendaFinal = valorVendaFinal;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
}
