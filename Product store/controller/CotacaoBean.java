package br.com.unip.tcc.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICotacaoItensService;
import br.com.unip.tcc.interfaces.ICotacaoService;
import br.com.unip.tcc.model.Cliente;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.model.CotacaoItens;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.vo.CotacaoItensVO;
import br.com.unip.tcc.vo.CotacaoVO;

@Controller("cotacaoBean")
public class CotacaoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICotacaoService cotacaoService;
	
	@Autowired
	private ICotacaoItensService cotacaoItensService;
	
	private Cotacao cotacao;
	private CotacaoItens cotacaoItem;
	private CotacaoVO cotacaoVo;
	private List<Cotacao> listAll;
	private static boolean detail = false;
	
	public CotacaoBean() {}
	
	public List<Cotacao> getListAll() {
		if(this.listAll == null) {
			if(this.cotacaoVo == null)
				this.cotacaoVo = new CotacaoVO();
			this.listAll = this.cotacaoService.findByFields(cotacaoVo);
		}
		return this.listAll;
	}
	
	public void setListAll(List<Cotacao> listAll) {
		this.listAll = listAll;
	}
	
	public Cotacao getCotacao() {
		if (this.cotacao == null) {
			this.cotacao = new Cotacao();
			this.cotacao.setValorTotal(new BigDecimal(0));
			this.cotacao.setSituacao(0);
		} else {
			if (this.cotacao.getId() != null)
				this.cotacao = this.cotacaoService.findById(this.cotacao.getId());
		}
        return this.cotacao;
	}
	
	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}
	
	public CotacaoItens getCotacaoItem() {
		return this.cotacaoItem;
	}

	public void setCotacaoItem(CotacaoItens cotacaoItem) {
		this.cotacaoItem = cotacaoItem;
	}

	public boolean isDetail() {
		return detail;
	}
	
	public void setDetail(boolean detail) {
		CotacaoBean.detail = detail;
	}

	public CotacaoVO getCotacaoVo() {
		if (this.cotacaoVo == null)
			this.cotacaoVo = new CotacaoVO();
		return this.cotacaoVo;
	}
	
	public void setCotacaoVo(CotacaoVO cotacaoVo) {
		this.cotacaoVo = cotacaoVo;
	}
	
	public String newRecord() {
		this.cotacao = new Cotacao();
		this.cotacao.setValorTotal(new BigDecimal(0));
		this.cotacao.setSituacao(0);
		this.cotacao.setNumero(this.cotacaoService.nextFieldRecord());
		return this.cotacaoService.newRecord();
	}
	
	public void save() throws FieldsUnfilledException {
		try {
			if (this.cotacaoService.isRecordExists(this.cotacao))
				FacesMessageUtil.showMessageError(Messages.M3);
			else {
					if (this.cotacao.getId() == null || this.cotacao.getId() == 0) {
						if (this.cotacaoService.insert(this.cotacao)) {
							this.listAll = this.cotacaoService.findByFields(this.cotacaoVo);
							FacesMessageUtil.showMessageInfo(Messages.M5);
						}
					} else {
						if (this.cotacaoService.update(this.cotacao))
								FacesMessageUtil.showMessageInfo(Messages.M6);
					}
			}
		} catch (FieldsUnfilledException e) {
			FacesMessageUtil.showMessageError(Messages.M2);
		}
	}
	
	public void remove() throws CannotDeleteException {
		try {
			this.cotacaoService.remove(this.cotacao);
			this.listAll = this.cotacaoService.findByFields(this.cotacaoVo);
		} catch (CannotDeleteException e) {
			FacesMessageUtil.showMessageInfo(Messages.M11);
		}
	}
	
	public String prepareUpdate() {
		return this.cotacaoService.prepareUpdate();
	}
	
	public String prepareDetail(Cotacao cotacao) {
		CotacaoBean.detail = true;
		this.cotacao = cotacao;
		return cotacaoService.prepareDetail();
	}
	
	public String cancel() {

		if (this.getCotacao().getSituacao() == 0) {
			this.cotacao.setSituacao(this.getCotacaoItensService().getSituacao(this.cotacao));
			
			try {
				this.getCotacaoService().update(this.cotacao);
				this.setListCotacaoItens();
			} catch (FieldsUnfilledException e) {
				e.printStackTrace();
			}

		}
		
		CotacaoBean.detail = false;
		return this.cotacaoService.cancel();
	}
	
	public void findByFields() {
		this.listAll = this.cotacaoService.findByFields(this.cotacaoVo);
	}
	
	public List<CotacaoItens> getListCotacaoItens() {
		this.setListCotacaoItens();
		return this.cotacao.getListCotacaoItens();
	}
	
	public void setListCotacaoItens() {
		CotacaoItensVO cotacaoItensVO = new CotacaoItensVO();
		cotacaoItensVO.setIdCotacao(this.cotacao.getId());
		this.cotacao.setListCotacaoItens(this.cotacaoItensService.findByFields(cotacaoItensVO));
		
		if (this.cotacao != null && this.cotacao.getId() != null)
			this.listAll = this.cotacaoService.findByFields(cotacaoVo);
	}
	
	public Cliente getCliente() {
		if (this.cotacao.getCliente() == null)
			this.cotacao.setCliente(new Cliente());
		return this.cotacao.getCliente();
	}
	
	public void newCompra() {
		if (this.cotacaoService.newCompra(this.cotacao))
			this.setListCotacaoItens();
	}
	
	public void cancelCotacao() {
		if (this.cotacaoService.cancelCotacao(this.cotacao))
			this.setListCotacaoItens();
	}
	
	public String newRecordItem() {
		return this.getCotacaoItensService().newRecord();
	}
	
	public String prepareUpdateItem() {
		return this.cotacaoService.prepareUpdateItem();
	}
	
	public String prepareDetailItem() {
		CotacaoItensBean.setDetail(true);
		return this.cotacaoService.prepareDetailItem();
	}
	
	public void removeItem() {
		try {
			this.cotacaoService.removeItem(this.cotacaoItem);
			CotacaoItensVO cotacaoItensVO = new CotacaoItensVO();
			cotacaoItensVO.setIdCotacao(this.cotacao.getId());
			this.cotacao.setListCotacaoItens(this.cotacaoItensService.findByFields(cotacaoItensVO));
		} catch (CannotDeleteException e) {
			FacesMessageUtil.showMessageInfo(Messages.M11);
		}
	}
	
	public void cancelItem() {
		if (this.cotacaoItensService.cancelItem(this.cotacaoItem)) {
			CotacaoItensVO cotacaoItensVO = new CotacaoItensVO();
			cotacaoItensVO.setIdCotacao(this.cotacao.getId());
			this.cotacao.setListCotacaoItens(this.cotacaoItensService.findByFields(cotacaoItensVO));
			this.getCotacao();
		}
	}
	
	public void newCompraItem() {
		if (this.cotacaoItensService.newCompra(cotacaoItem)) {
			CotacaoItensVO cotacaoItensVO = new CotacaoItensVO();
			cotacaoItensVO.setIdCotacao(this.cotacao.getId());
			this.cotacao.setListCotacaoItens(this.cotacaoItensService.findByFields(cotacaoItensVO));
			this.getCotacao();
		}
	}
	
	public ICotacaoService getCotacaoService() {
		return cotacaoService;
	}

	public void setCotacaoService(ICotacaoService cotacaoService) {
		this.cotacaoService = cotacaoService;
	}

	public ICotacaoItensService getCotacaoItensService() {
		return cotacaoItensService;
	}

	public void setCotacaoItensService(ICotacaoItensService cotacaoItensService) {
		this.cotacaoItensService = cotacaoItensService;
	}
}
