package br.com.unip.tcc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="compraitens")
public class CompraItens implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	@OneToOne
	@JoinColumn(name="idCompra")
 	private Compra compra;
	@OneToOne
	@JoinColumn(name="idProduto")
 	private Produto produto;
 	@Column(name="valor")
 	private BigDecimal valor;
 	@OneToOne
	@JoinColumn(name="idCotacaoItens")
 	private CotacaoItens cotacaoItens;
 	@Column(name="quantidade")
 	private Integer quantidade;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public CotacaoItens getCotacaoItens() {
		return cotacaoItens;
	}
	public void setCotacaoItens(CotacaoItens cotacaoItens) {
		this.cotacaoItens = cotacaoItens;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
