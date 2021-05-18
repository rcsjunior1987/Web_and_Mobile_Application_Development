package br.com.unip.tcc.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.unip.tcc.dao.CompraItensDAO;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICompraItensService;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.vo.CompraItensVO;

@Controller("compraItensBean")
public class CompraItensBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ICompraItensService compraItensService;

	private Compra compra;
	private CompraItens compraItem;
	public static boolean detail = false;
	private List<Produto> listProduto;
	
	public CompraItensBean() {}
	
	public CompraItens getCompraItem() {
        return this.compraItem;
	}
	
	public void setCompraItem(CompraItens compraItem) {
		this.compraItem = compraItem;
	}
	
	public boolean isDetail() {
		return detail;
	}

	public void setDetail(boolean detail) {
		CompraItensBean.detail = detail;
	}
	
	public String newRecord(Compra compra) {
		if (this.compraItem == null) {
			this.compraItem = new CompraItens();
			this.compraItem.setCompra(compra);
			this.compraItem.setQuantidade(0);
			this.compraItem.setValor(new BigDecimal(0));
		}
		return this.compraItensService.newRecord();
	}
	public void save() throws FieldsUnfilledException {
		try {
				if (this.compraItem.getId() == null || this.compraItem.getId() == 0) {
					if (new CompraItensDAO().insert(this.compraItem))
						FacesMessageUtil.showMessageInfo(Messages.M5);
					} else {
						if (new CompraItensDAO().update(this.compraItem))
							FacesMessageUtil.showMessageInfo(Messages.M6);
				}
				CompraItens compraItens = new CompraItens();
				compraItens.setCompra(this.compraItem.getCompra());
				compraItens.setQuantidade(0);
				compraItens.setValor(new BigDecimal(0));
			} catch (FieldsUnfilledException e) {
				FacesMessageUtil.showMessageError(Messages.M2);
			}
	}
	public String remove() throws CannotDeleteException {
		try {
			return this.compraItensService.remove(this.compraItem);
		} catch (CannotDeleteException e) {
				FacesMessageUtil.showMessageInfo(Messages.M11);
		}
		return null;
	}
	
	public String cancel() {
		CompraItensBean.detail = false;
		return this.compraItensService.cancel();
	}
	
	public void findByFields(CompraItensVO compraItensVo) {
		this.compraItensService.findByFields(compraItensVo);
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	public void setProduto(Produto produto) {
		this.compraItem.setProduto(produto);
	}
	public Produto getProduto() {
		if (this.compraItem.getProduto() == null)
			this.compraItem.setProduto(new Produto());
		return this.compraItem.getProduto();
	}
	public List<Produto> getListProduto() {
		return listProduto;
	}
	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}
}