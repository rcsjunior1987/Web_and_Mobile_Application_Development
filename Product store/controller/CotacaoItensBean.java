package br.com.unip.tcc.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICotacaoItensService;
import br.com.unip.tcc.interfaces.ICotacaoService;
import br.com.unip.tcc.interfaces.IProdutoService;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.model.CotacaoItens;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.vo.CotacaoItensVO;

@Controller("cotacaoItensBean")
public class CotacaoItensBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICotacaoItensService cotacaoItensService;
	
	@Autowired
	private IProdutoService produtoService;

	private Cotacao cotacao;
	private CotacaoItens cotacaoItem;
	public static boolean detail = false;

	public CotacaoItensBean() {
	}

	public CotacaoItens getCotacaoItem() {
		if (this.cotacaoItem == null)
			this.newRecord();
		return this.cotacaoItem;
	}

	public void setCotacaoItem(CotacaoItens cotacaoItem) {
		this.cotacaoItem = cotacaoItem;
	}

	public boolean isDetail() {
		return detail;
	}

	public static void setDetail(boolean detail) {
		CotacaoItensBean.detail = detail;
	}

	public void newRecord() {
		this.cotacaoItem = new CotacaoItens();
		this.cotacaoItem.setCotacao(cotacao);
		this.cotacaoItem.setData(Calendar.getInstance());
		this.cotacaoItem.setValor(new BigDecimal(0));
		this.cotacaoItem.setQuantidade(0);
		this.cotacaoItem.setSituacao(0);
	}

	public void save() throws FieldsUnfilledException {
		try {
			if (this.cotacaoItensService.isRecordExists(this.cotacaoItem))
				FacesMessageUtil.showMessageError(Messages.M3);
			else {
					if (this.cotacaoItem.getId() == null || this.cotacaoItem.getId() == 0) {
						if (this.cotacaoItensService.insert(this.cotacaoItem))
							this.cotacao.getListCotacaoItens().add(cotacaoItem);
							FacesMessageUtil.showMessageInfo(Messages.M5);
					} else {
						if (this.cotacaoItensService.update(this.cotacaoItem))
							FacesMessageUtil.showMessageInfo(Messages.M6);
					}
					this.newRecord();
			}
		} catch (FieldsUnfilledException e) {
			FacesMessageUtil.showMessageError(Messages.M2);
		}
	}

	public String remove() throws CannotDeleteException {
		try {
			return this.cotacaoItensService.remove(this.cotacaoItem);
		} catch (CannotDeleteException e) {
			FacesMessageUtil.showMessageInfo(Messages.M11);
		}
		return null;
	}

	public String cancel() {
		CotacaoItensBean.detail = false;
		return this.cotacaoItensService.cancel();
	}

	public void findByFields(CotacaoItensVO cotacaoItensVO) {
		this.cotacaoItensService.findByFields(cotacaoItensVO);
	}

	public Cotacao getCotacao() {
		return cotacao;
	}

	public void setCotacao(Cotacao cotacao) {
		this.setCotacaoItem(null);
		this.cotacao = cotacao;
	}

	public void setProduto(Produto produto) {
		this.cotacaoItem.setProduto(produto);
	}

	public Produto getProduto() {
		if (this.cotacaoItem.getProduto() == null)
			this.cotacaoItem.setProduto(new Produto());
		return this.cotacaoItem.getProduto();
	}

	public Fornecedor getFornecedor() {
		if (this.cotacaoItem.getFornecedor() == null)
			this.cotacaoItem.setFornecedor(new Fornecedor());
		return this.cotacaoItem.getFornecedor();
	}

	public ICotacaoItensService getCotacaoItensService() {
		return cotacaoItensService;
	}

	public void setCotacaoItensService(ICotacaoItensService cotacaoItensService) {
		this.cotacaoItensService = cotacaoItensService;
	}

	public void sujerirPreco() {
		if (this.cotacaoItem.getProduto() != null && this.cotacaoItem.getProduto().getId() != null) {
			Produto produto = this.produtoService.findById(this.cotacaoItem.getProduto().getId());
			this.cotacaoItem.setValor(produto.getPrecoVenda());
		}
	}

	public IProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(IProdutoService produtoService) {
		this.produtoService = produtoService;
	}
}