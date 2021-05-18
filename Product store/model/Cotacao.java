package br.com.unip.tcc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="cotacao", schema = "tcc")
public class Cotacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column 
	private Integer id;
	@Column(name="numero")
    private Integer numero;
	@Column(name="situacao")
    private Integer situacao;
	@Transient
	private BigDecimal valorTotal;
	@OneToMany(mappedBy="cotacao", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	@OrderBy(value="id DESC")
	@Fetch(FetchMode.SUBSELECT)
	private List<CotacaoItens> listCotacaoItens;
	@OneToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;
	public Cotacao() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getSituacao() {
		return situacao;
	}
	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
	public BigDecimal getValorTotal() {
		BigDecimal valorTotal = new BigDecimal(0);
		if(this.listCotacaoItens != null) {
			for (CotacaoItens cotacaoItens: this.listCotacaoItens) {
				valorTotal = valorTotal.add(cotacaoItens.getValor());
			}
			this.valorTotal = valorTotal;
		}
		return this.valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<CotacaoItens> getListCotacaoItens() {
		return listCotacaoItens;
	}
	public void setListCotacaoItens(List<CotacaoItens> listCotacaoItens) {
		this.listCotacaoItens = listCotacaoItens;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getTextSituacao() {
		switch (this.situacao) {
			case 0: return "Em aberto";
			case 1: return "Cancelado";
			case 2: return "Confirmado";
		}
		return null;
	}
}
