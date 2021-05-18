package br.com.unip.tcc.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IProdutoBean;
import br.com.unip.tcc.interfaces.IProdutoService;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.service.ProdutoService;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.vo.ProdutoVO;

@Controller("produtoBean")
public class ProdutoBean implements IProdutoBean, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public IProdutoService produtoService;
	
	private Produto produto;
	private ProdutoVO produtoVo;
	private List<Produto> listAll = null;
	private static boolean detail = false;
	 
	public ProdutoBean() {}
	
	@Override
	public List<Produto> getListAll() {
		if(this.listAll == null) {
			if(this.produtoVo == null)
				this.produtoVo = new ProdutoVO();
			this.listAll = this.produtoService.findByFields(produtoVo);
		}
		return this.listAll;
	}
	
	@Override
	public void setListAll(List<Produto> listAll) {
		this.listAll = listAll;
	}
	
	@Override
	public Produto getProduto() {
		if (this.produto == null) {
			this.produto = new Produto();
		}
        return this.produto;
	}
	
	@Override
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Override
	public boolean isDetail() {
		return detail;
	}
	
	@Override
	public void setDetail(boolean detail) {
		ProdutoBean.detail = detail;
	}
	
	@Override
	public ProdutoVO getProdutoVo() {
		if (this.produtoVo == null) {
			this.produtoVo = new ProdutoVO();
			this.produtoVo.setValorCompraInicial(new BigDecimal(0.0));
			this.produtoVo.setValorCompraFinal(new BigDecimal(1000));
			this.produtoVo.setValorVendaInicial(new BigDecimal(0.0));
			this.produtoVo.setValorVendaFinal(new BigDecimal(1000));
		}
		return this.produtoVo;
	}
	
	@Override
	public void setProdutoVo(ProdutoVO produtoVo) {
		this.produtoVo = produtoVo;
	}
	
	@Override
	public String newRecord() {
		this.produto = new Produto();
		this.produto.setPrecoCompra(new BigDecimal(0));
		this.produto.setPrecoVenda(new BigDecimal(0));
		return this.produtoService.newRecord(produtoVo);
	}
	
	@Override
	public void save() throws FieldsUnfilledException {
		try {
				if (this.produtoService.isRecordExists(this.produto)) {
					FacesMessageUtil.showMessageError(Messages.M3);
				}
				else {
						if (this.produto.getId() == null || this.produto.getId() == 0) {
							if (this.produtoService.insert(this.produto)) {
								this.listAll = this.produtoService.findByFields(this.produtoVo);
								FacesMessageUtil.showMessageInfo(Messages.M5);
							}
						} else {
							if (this.produtoService.update(this.produto))
								FacesMessageUtil.showMessageInfo(Messages.M6);
						}
				}
			} catch (FieldsUnfilledException e) {
				FacesMessageUtil.showMessageError(Messages.M2);
			}
	}
	
	@Override
	public void remove() throws CannotDeleteException {
		try {
				this.produtoService.remove(this.produto);
				this.listAll =  this.produtoService.findByFields(this.produtoVo);
		} catch (CannotDeleteException e) {
				FacesMessageUtil.showMessageInfo(Messages.M11);
		}
	}
	
	@Override
	public String prepareUpdate(Produto produto) {
		this.produto = produto;
		return this.produtoService.prepareUpdate(this.produto);
	}
	@Override
	public String prepareDetail(Produto produto) {
		ProdutoBean.detail = true;
		this.produto = produto;
		return produtoService.prepareDetail(produto);
	}
	
	@Override
	public String cancel() {
		ProdutoBean.detail = false;
		return this.produtoService.cancel(this.produto);
	}
	
	@Override
	public void findByFields() {
		this.listAll = this.produtoService.findByFields(this.produtoVo);
	}

	@Override
	public ProdutoService getProdutoService() {
		return null;
	}

	@Override
	public void setProdutoService(ProdutoService produtoService) {
	}

}
