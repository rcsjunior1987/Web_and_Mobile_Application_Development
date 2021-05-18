package br.com.unip.tcc.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
public class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence")
	//@Column(name="id")
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="nome", length=50)
	private String nome;
	@Column(name="dataNascimento")
	private Calendar dataNascimento;
	@Column(name="rg", length=9)
	private String rg;
	@Column(name="cpf", length=14)
	private String cpf;
	@Column(name="sexo", length=1)
	private String sexo;
	@Column(name="telefoneFixo", length=12)
	private String telefoneFixo;
	@Column(name="telefoneCelular", length=14)
	private String telefoneCelular;
	@Column(name="endereco", length=30)
	private String endereco;
	@Column(name="numero")
	private int numero;
	@Column(name="bairro", length=30)
	private String bairro;
	@Column(name="cidade", length=30)
	private String cidade;
	@Column(name="estado", length=2)
	private String estado;
	@Column(name="complemento", length=30)
	private String complemento;
	@Column(name="cep", length=8)
	private String cep;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public String getTelefoneFixo() {
		return telefoneFixo;
	}
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTextSexo() {
		switch (sexo) {
		case "M":
			return "Masculino";
		case "F":
			return "Feminino";
		default:
			break;
		}
		return null;
	}
}
