package br.com.unip.tcc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="compra")
public class Compra implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	@Column(name="numero")
	private Integer numero;
	@Column(name="dataDaCompra")
	private Calendar dataDaCompra;
	@OneToOne
	@JoinColumn(name="idFornecedor")
	private Fornecedor fornecedor;
	@Transient
	private BigDecimal valorTotal;
	@OneToMany(mappedBy="compra", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	@OrderBy(value="id DESC")
	@Fetch(FetchMode.SUBSELECT)
	private List<CompraItens> listCompraItens;
	@Column(name="situacao")
	private Integer situacao;
	@OneToOne
	@JoinColumn(name="idCotacao")
	private Cotacao cotacao;
	public Compra() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Calendar getDataDaCompra() {
		return dataDaCompra;
	}
	public void setDataDaCompra(Calendar dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public BigDecimal getValorTotal() {
		BigDecimal valorTotal = new BigDecimal(0);
		if(this.listCompraItens != null) {
			for (CompraItens compraItens: this.listCompraItens) {
				valorTotal = valorTotal.add(compraItens.getValor());
			}
			this.valorTotal = valorTotal;
		}
		return this.valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<CompraItens> getListCompraItens() {
		return listCompraItens;
	}
	public void setListCompraItens(List<CompraItens> listCompraItens) {
		this.listCompraItens = listCompraItens;
	}
	public Integer getSituacao() {
		return situacao;
	}
	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
	public String getTextSituacao() {
		switch (this.situacao) {
			case 0: return "Em aberto";
			case 1: return "Cancelado";
			case 2: return "Confirmado";
		}
		return null;
	}
	public Cotacao getCotacao() {
		return cotacao;
	}
	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}
}
