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
@Table(name="venda")
public class Venda implements Serializable {
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
	@Column(name="dataDaVenda")
	private Calendar dataDaVenda;
	@Column(name="situacao")
	private Integer situacao;
	@Transient
	private BigDecimal valorTotal;
	@OneToMany(mappedBy="venda", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	@OrderBy(value="id DESC")
	@Fetch(FetchMode.SUBSELECT)
	private List<VendaItens> listVendaItens;
	@OneToOne
	@JoinColumn(name="idCliente")
	
	private Cliente cliente;
	
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
	public Calendar getDataDaVenda() {
		return dataDaVenda;
	}
	public void setDataDaVenda(Calendar dataDaVenda) {
		this.dataDaVenda = dataDaVenda;
	}
	public Integer getSituacao() {
		return situacao;
	}
	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
	public BigDecimal getValorTotal() {
		BigDecimal valorTotal = new BigDecimal(0);
		if(this.listVendaItens != null) {
			for (VendaItens vendaItens: this.listVendaItens) {
				valorTotal = valorTotal.add(vendaItens.getValor());
			}
			this.valorTotal = valorTotal;
		}
		return this.valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<VendaItens> getListVendaItens() {
		return listVendaItens;
	}
	public void setListVendaItens(List<VendaItens> listVendaItens) {
		this.listVendaItens = listVendaItens;
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
			case 2: return "Confirmado";
		}
		return null;
	}
}
