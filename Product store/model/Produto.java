package br.com.unip.tcc.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="produto")
public class Produto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	@Column(name="descricao", length=50)
	private String descricao;
	@Column(name="marca", length=30)
	private String marca;
	@Column(name="precoCompra")
	private BigDecimal precoCompra;
	@Column(name="precoVenda")
	private BigDecimal precoVenda;
	@Transient
	private BigDecimal lucroValor;
	@Transient
	private Integer lucroPorcentagem;
	@Column(name="codigoBarras")
	private BigDecimal codigoBarras;
	@OneToMany(mappedBy="produto", fetch=FetchType.EAGER)
	private List<Estoque> listEstoque;
	@Transient
	private Integer qtdeEstoque;
	public Produto() {}
	public Produto(String descricao, Integer id) {
		super();
		this.descricao = descricao;
		this.id = id;
	}
	public Produto(Integer id) {
		super();
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public BigDecimal getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(BigDecimal precoCompra) {
		this.precoCompra = precoCompra;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	public BigDecimal getLucroValor() {
		BigDecimal resultado = new BigDecimal("0");
		if (this.precoVenda != null && this.precoCompra != null) {
			BigDecimal precoVenda  = new BigDecimal(this.precoVenda.toString());
			BigDecimal precoCompra = new BigDecimal(this.precoCompra.toString());
			resultado = precoVenda.subtract(precoCompra);
		}
		return resultado;
	}
	public void setLucroValor(BigDecimal lucroValor) {
		this.lucroValor = lucroValor;
	}
	public Integer getLucroPorcentagem() {
		BigDecimal resultado = new BigDecimal(0.0);
		if (this.precoVenda != null && this.precoCompra != null) {
			BigDecimal precoVenda  = new BigDecimal(this.precoVenda.toString());
			BigDecimal precoCompra = new BigDecimal(this.precoCompra.toString());
			BigDecimal lucroValor =  precoVenda.subtract(precoCompra);
			if (precoCompra.compareTo(new BigDecimal(0.0)) != 0) {
				resultado = lucroValor.divide(precoCompra, BigDecimal.ROUND_UP);
				resultado = resultado.multiply(new BigDecimal(100));
			}
		}
		return resultado.intValue();
	}
	public void setLucroPorcentagem(Integer lucroPorcentagem) {
		this.lucroPorcentagem = lucroPorcentagem;
	}
	public BigDecimal getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(BigDecimal codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public List<Estoque> getListEstoque() {
		return listEstoque;
	}
	public void setListEstoque(List<Estoque> listEstoque) {
		this.listEstoque = listEstoque;
	}
	public Integer getQtdeEstoque() {
		Integer qtdeEstoque = 0;
		if (listEstoque != null) {
			for (Estoque estoque : listEstoque) {
				qtdeEstoque+=estoque.getQuantidade();
			}
		}
		return qtdeEstoque;
	}
	public void setQtdeEstoque(Integer qtdeEstoque) {
		this.qtdeEstoque = qtdeEstoque;
	}
}
