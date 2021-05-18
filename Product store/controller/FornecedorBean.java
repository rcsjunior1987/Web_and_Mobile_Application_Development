package br.com.unip.tcc.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IFornecedorBean;
import br.com.unip.tcc.interfaces.IFornecedorService;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.service.FornecedorService;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.vo.FornecedorVO;

@Controller("fornecedorBean")
public class FornecedorBean implements IFornecedorBean, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IFornecedorService fornecedorService; 
	
	private Fornecedor fornecedor;
	private FornecedorVO fornecedorVo;
	private List<Fornecedor> listAll = null;
	private static boolean detail = false;
	
	public FornecedorBean() {}
	
	@Override
	public List<Fornecedor> getListAll() {
		if(this.listAll == null) {
			if(this.fornecedorVo == null) {
				this.fornecedorVo = new FornecedorVO();
			}
			this.listAll = this.fornecedorService.findByFields(fornecedorVo);
		}
		return this.listAll;
	}
	@Override
	public void setListAll(List<Fornecedor> listAll) {
		this.listAll = listAll;
	}
	@Override
	public Fornecedor getFornecedor() {
		if (this.fornecedor == null) {
			this.fornecedor = new Fornecedor();
		}
        return this.fornecedor;
	}
	@Override
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	@Override
	public boolean isDetail() {
		return detail;
	}
	@Override
	public void setDetail(boolean detail) {
		FornecedorBean.detail = detail;
	}
	@Override
	public FornecedorVO getFornecedorVo() {
		if (this.fornecedorVo == null)
			this.fornecedorVo = new FornecedorVO();
		return this.fornecedorVo;
	}
	@Override
	public void setFornecedorVo(FornecedorVO fornecedorVo) {
		this.fornecedorVo = fornecedorVo;
	}
	@Override
	public String newRecord() {
		this.fornecedor = new Fornecedor();
		return this.fornecedorService.newRecord(this.fornecedorVo);
	}
	@Override
	public void save() throws FieldsUnfilledException {
		try {
				if (this.fornecedorService.isRecordExists(this.fornecedor))
					FacesMessageUtil.showMessageError(Messages.M3);
				else {
					if (this.fornecedor.getId() == null || this.fornecedor.getId() == 0) {
						if (this.fornecedorService.insert(this.fornecedor)) {
							this.listAll = this.fornecedorService.findByFields(this.fornecedorVo);
							FacesMessageUtil.showMessageInfo(Messages.M5);
						}
					} else {
						if (this.fornecedorService.update(this.fornecedor))
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
			this.fornecedorService.remove(this.fornecedor);
			this.listAll = this.fornecedorService.findByFields(this.fornecedorVo);
		} catch (CannotDeleteException e) {
			FacesMessageUtil.showMessageInfo(Messages.M11);
		}
	}
	@Override
	public String prepareUpdate(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
		return this.fornecedorService.prepareUpdate(this.fornecedor);
	}
	@Override
	public String prepareDetail(Fornecedor fornecedor) {
		FornecedorBean.detail = true;
		this.fornecedor = fornecedor;
		return this.fornecedorService.prepareDetail(this.fornecedor);
	}
	@Override
	public String cancel() {
		FornecedorBean.detail = false;
		return this.fornecedorService.cancel(this.fornecedor);
	}
	@Override
	public void findByFields() {
		this.listAll = this.fornecedorService.findByFields(this.fornecedorVo);
	}
	@Override
	public FornecedorService getFornecedorService() {
		return null;
	}
	@Override
	public void setFornecedorService(IFornecedorService fornecedorService) {
	}
}
