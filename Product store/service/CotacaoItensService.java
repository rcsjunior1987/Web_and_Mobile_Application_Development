package br.com.unip.tcc.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICompraItensService;
import br.com.unip.tcc.interfaces.ICompraService;
import br.com.unip.tcc.interfaces.ICotacaoItensDao;
import br.com.unip.tcc.interfaces.ICotacaoItensService;
import br.com.unip.tcc.interfaces.ICotacaoService;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.model.CotacaoItens;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.system.Screen;
import br.com.unip.tcc.util.FacesUtil;
import br.com.unip.tcc.vo.CotacaoItensVO;

@Service("cotacaoItensService")
public class CotacaoItensService implements ICotacaoItensService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICotacaoItensDao cotacaoItensDao;
	
	@Autowired
	private ICotacaoService cotacaoService;
	
	@Autowired
	private ICompraService compraService;
	
	@Autowired
	private ICompraItensService compraItensService;
	
	@Override
	public String newRecord() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COTACAOITENS);
	}

	@Override
	public boolean insert(CotacaoItens entity) throws FieldsUnfilledException {
		return this.cotacaoItensDao.insert(entity);
	}

	@Override
	public boolean update(CotacaoItens entity) throws FieldsUnfilledException {
		return this.cotacaoItensDao.update(entity);
	}

	@Override
	public String remove(CotacaoItens entity) throws CannotDeleteException {
		this.cotacaoItensDao.remove(entity);
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COTACAO);
	}

	@Override
	public String cancel() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COTACAO);
	}

	@Override
	public CotacaoItens findById(Integer id) {
		return this.cotacaoItensDao.findById(id);
	}

	@Override
	public List<CotacaoItens> findByFields(CotacaoItensVO cotacaoItensVO) {
		return this.cotacaoItensDao.findByFields(cotacaoItensVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CotacaoItens> findByFields(String jpql) {
		return (List<CotacaoItens>) this.cotacaoItensDao.findByFields(jpql);
	}
	
	@Override
	public boolean isRecordExists(CotacaoItens cotacaoItens) {
		return this.cotacaoItensDao.isRecordExists(cotacaoItens);
	}
	
	@Override
	public boolean newCompra(CotacaoItens cotacaoItem) {
		if(cotacaoItem != null) {
			try {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor = cotacaoItem.getFornecedor();

				Produto produto = new Produto();
				produto = cotacaoItem.getProduto();
				
				Compra compra = new Compra();
				compra.setDataDaCompra(Calendar.getInstance());
				compra.setFornecedor(fornecedor);
				compra.setNumero(this.compraService.nextFieldRecord());
				compra.setSituacao(0);
				this.compraService.insert(compra);
				
				CompraItens compraItens = new CompraItens();
				compraItens.setCompra(compra);
				compraItens.setCotacaoItens(cotacaoItem);
				compraItens.setProduto(produto);
				compraItens.setValor(cotacaoItem.getValor());
				compraItens.setQuantidade(cotacaoItem.getQuantidade());
				this.compraItensService.insert(compraItens);
				
				cotacaoItem.setSituacao(2);
				this.cotacaoItensDao.update(cotacaoItem);
				
				Cotacao cotacao = this.cotacaoService.findById(cotacaoItem.getCotacao().getId());
				cotacao.setSituacao(this.getSituacao(cotacao));
				this.cotacaoService.update(cotacao);
				
				return true;
			} catch (FieldsUnfilledException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean cancelItem(CotacaoItens cotacaoItem) {
		if(cotacaoItem != null) {
			try {
				cotacaoItem.setSituacao(1);
				this.update(cotacaoItem);
				
				Cotacao cotacao = this.cotacaoService.findById(cotacaoItem.getCotacao().getId());
				cotacao.setSituacao(this.getSituacao(cotacao));
				this.cotacaoService.update(cotacao);
				
				return true;
			} catch (FieldsUnfilledException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public ICotacaoItensDao getCotacaoItensDao() {
		return cotacaoItensDao;
	}

	public void setCotacaoItensDao(ICotacaoItensDao cotacaoItensDao) {
		this.cotacaoItensDao = cotacaoItensDao;
	}

	public ICompraService getCompraService() {
		return compraService;
	}

	public void setCompraService(ICompraService compraService) {
		this.compraService = compraService;
	}

	public ICompraItensService getCompraItensService() {
		return compraItensService;
	}

	public void setCompraItensService(ICompraItensService compraItensService) {
		this.compraItensService = compraItensService;
	}
	
	public ICotacaoService getCotacaoService() {
		return cotacaoService;
	}

	public void setCotacaoService(ICotacaoService cotacaoService) {
		this.cotacaoService = cotacaoService;
	}

	@Override
	public Integer getCotacaoItensAberto(Cotacao cotacao) {
		return this.cotacaoItensDao.getCotacaoItensAberto(cotacao);
	}
	
	@Override
	public Integer getCotacaoItensConfirmado(Cotacao cotacao) {
		return this.cotacaoItensDao.getCotacaoItensConfirmado(cotacao);
	}
	@Override
	public Integer getCotacaoItensCancelado(Cotacao cotacao) {
		return this.cotacaoItensDao.getCotacaoItensCancelado(cotacao);
	}
	
	@Override
	public boolean insertAnyRecord(List<CotacaoItens> listRecord) {
		return this.cotacaoItensDao.insertAnyRecord(listRecord);
	}
	
	@Override
	public boolean updateAnyRecord(List<CotacaoItens> listRecord) {
		return this.cotacaoItensDao.updateAnyRecord(listRecord);
	}
	
	@Override
	public Integer getSituacao(Cotacao cotacao) {
		if (this.getCotacaoItensAberto(cotacao) != 0)
			return 0;

		if (this.getCotacaoItensAberto(cotacao) == 0 && this.getCotacaoItensCancelado(cotacao) == 0)
			return 0;
		
		if ((this.getCotacaoItensConfirmado(cotacao) >=  this.getCotacaoItensCancelado(cotacao)))
			return 2;
		
		if ((this.getCotacaoItensConfirmado(cotacao) <  this.getCotacaoItensCancelado(cotacao)))
			return 1;
		
		return 0;
	}
	
}
