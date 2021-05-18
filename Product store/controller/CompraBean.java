package br.com.unip.tcc.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICompraItensService;
import br.com.unip.tcc.interfaces.ICompraService;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.vo.CompraVO;

@Controller("compraBean")
public class CompraBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ICompraService compraService;
	
	@Autowired
	private ICompraItensService compraItensService;
	
	private Compra compra;
	private CompraItens compraItem;
	private CompraVO compraVo;
	private List<Compra> listAll;
	private static boolean detail = false;
	
	public CompraBean() {}
	
	public List<Compra> getListAll() {
		if(this.listAll == null) {
			if(this.compraVo == null) {
				this.compraVo = new CompraVO();
			}
			this.listAll = this.compraService.findByFields(compraVo);
		}
		return this.listAll;
	}
	
	public void setListAll(List<Compra> listAll) {
		this.listAll = listAll;
	}
	
	public Compra getCompra() {
		if (this.compra == null) {
			this.compra = new Compra();
			this.compra.setDataDaCompra(Calendar.getInstance());
			this.compra.setSituacao(0);
			this.compra.setValorTotal(new BigDecimal(0));
		}
        return this.compra;
	}
	
	public void setCompra(Compra compra) {
		if (compra != null)
			this.compra = compra;
	}
	
	public CompraItens getCompraItem() {
		return compraItem;
	}

	public void setCompraItem(CompraItens compraItem) {
		System.out.println("aaaaaaooo");
		this.compraItem = compraItem;
	}

	public boolean isDetail() {
		return detail;
	}
	public void setDetail(boolean detail) {
		CompraBean.detail = detail;
	}

	public CompraVO getCompraVo() {
		if (this.compraVo == null)
			this.compraVo = new CompraVO();
		return this.compraVo;
	}
	public void setCompraVo(CompraVO compraVo) {
		this.compraVo = compraVo;
	}
	
	public String newRecord() {
		this.compra = new Compra();
		this.compra.setDataDaCompra(Calendar.getInstance());
		this.compra.setSituacao(0);
		this.compra.setValorTotal(new BigDecimal(0));
		return this.compraService.newRecord();
	}
	
	public void save() throws FieldsUnfilledException {
		try {
				if (this.compraService.isRecordExists(this.compra))
					FacesMessageUtil.showMessageError(Messages.M3);
				else {
						if (this.compra.getId() == null || this.compra.getId() == 0) {
							if (this.compraService.insert(this.compra))
								FacesMessageUtil.showMessageInfo(Messages.M5);
						} else {
							if (this.compraService.update(this.compra))
								FacesMessageUtil.showMessageInfo(Messages.M6);
						}
				}
		} catch (FieldsUnfilledException e) {
			FacesMessageUtil.showMessageError(Messages.M2);
		}
	}
	
	public void remove() throws CannotDeleteException {
		try {
				this.compraService.remove(this.compra);
				this.listAll = this.compraService.findByFields(this.compraVo);
		} catch (CannotDeleteException e) {
				FacesMessageUtil.showMessageInfo(Messages.M11);
		}
	}
	
	public String prepareUpdate(Compra compra) {
		this.compra = compra;
		return this.compraService.prepareUpdate();
	}
	
	public String prepareDetail(Compra compra) {
		CompraBean.detail = true;
		this.compra = compra;
		return this.compraService.prepareDetail();
	}
	
	public String cancel() {
		CompraBean.detail = false;
		return this.compraService.cancel();
	}
	
	public void findByFields() {
		this.listAll = this.compraService.findByFields(this.compraVo);
	}
	
	public String prepareUpdateItem() {
		return this.compraService.prepareUpdateItem();
		
	}
	
	public List<CompraItens> getListCompraItens() {
		return this.compra.getListCompraItens();
	}
	
	public Fornecedor getFornecedor() {
		if (this.compra.getFornecedor() == null)
			this.compra.setFornecedor(new Fornecedor());
		return this.compra.getFornecedor();
	}
	
	public void confirmCompra() {
		this.compraService.confirmCompra(this.compra);
	}

	public ICompraService getCompraService() {
		return compraService;
	}

	public void setCompraService(ICompraService compraService) {
		this.compraService = compraService;
	}
}
