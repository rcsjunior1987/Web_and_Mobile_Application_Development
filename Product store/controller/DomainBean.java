package br.com.unip.tcc.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.unip.tcc.interfaces.IClienteBean;
import br.com.unip.tcc.interfaces.IClienteService;
import br.com.unip.tcc.interfaces.ICotacaoItensService;
import br.com.unip.tcc.interfaces.IFornecedorBean;
import br.com.unip.tcc.interfaces.IFornecedorService;
import br.com.unip.tcc.interfaces.IProdutoBean;
import br.com.unip.tcc.interfaces.IProdutoService;
import br.com.unip.tcc.model.Cliente;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.vo.ClienteVO;
import br.com.unip.tcc.vo.FornecedorVO;
import br.com.unip.tcc.vo.ProdutoVO;
@Controller("domainBean")
public class DomainBean {
	public DomainBean() {}

	@Autowired
	private IProdutoService produtoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IFornecedorService fornecedorService;
	
	public List<Produto> getProdutos() {
		return this.produtoService.findByFields(new ProdutoVO());

	}
	public List<Cliente> getClientes() {
		return this.clienteService.findByFields(new ClienteVO());
	}

	public List<Fornecedor> getFornecedor() {
		return this.fornecedorService.findByFields(new FornecedorVO());
	}
	public IProdutoService getProdutoService() {
		return produtoService;
	}
	public void setProdutoService(IProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	public IClienteService getClienteService() {
		return clienteService;
	}
	public void setClienteService(IClienteService clienteService) {
		this.clienteService = clienteService;
	}
	public IFornecedorService getFornecedorService() {
		return fornecedorService;
	}
	public void setFornecedorService(IFornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}
}
