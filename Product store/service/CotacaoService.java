package br.com.unip.tcc.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICompraItensService;
import br.com.unip.tcc.interfaces.ICompraService;
import br.com.unip.tcc.interfaces.ICotacaoDao;
import br.com.unip.tcc.interfaces.ICotacaoItensDao;
import br.com.unip.tcc.interfaces.ICotacaoItensService;
import br.com.unip.tcc.interfaces.ICotacaoService;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.model.CotacaoItens;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.system.Screen;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.util.FacesUtil;
import br.com.unip.tcc.vo.CotacaoVO;

@Service("cotacaoService")
public class CotacaoService implements ICotacaoService {

	@Autowired
	private ICotacaoDao cotacaoDao;
	
	@Autowired
	private ICotacaoItensDao cotacaoItensDao;
	
	@Autowired
	private ICotacaoItensService cotacaoItensService;
	
	@Autowired
	private ICompraService compraService;
	
	@Autowired
	private ICompraItensService compraItensService;
	
	@Override
	public String newRecord() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COTACAO);
	}

	@Override
	public boolean insert(Cotacao entity) throws FieldsUnfilledException {
		return this.cotacaoDao.insert(entity);
	}

	@Override
	public boolean update(Cotacao entity) throws FieldsUnfilledException {
		return this.cotacaoDao.update(entity);
	}

	@Override
	public void remove(Cotacao entity) throws CannotDeleteException {
		this.cotacaoDao.remove(entity);
	}

	@Override
	public String cancel() {
		return FacesUtil.facesRedirectUrl(Screen.INCLUIR_COTACAO);
	}

	@Override
	public String prepareUpdate() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COTACAO);
	}

	@Override
	public String prepareDetail() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COTACAO);
	}

	@Override
	public Cotacao findById(Integer id) {
		return this.cotacaoDao.findById(id);
	}

	@Override
	public List<Cotacao> findByFields(CotacaoVO cotacaoVO) {
		return this.cotacaoDao.findByFields(cotacaoVO);
	}

	@Override
	public boolean isRecordExists(Cotacao cotacao) {
		return this.cotacaoDao.isRecordExists(cotacao);
	}

	@Override
	public boolean newCompra(Cotacao cotacao) {
		if (cotacao.getListCotacaoItens().size() == 0) {
			FacesMessageUtil.showMessageInfo(Messages.M21);
			return false;
		}

		@SuppressWarnings("unchecked")
		List<CotacaoItens> listCotacaoFornecedores = (List<CotacaoItens>) this.grupingRecords(cotacao);
		
		StringBuilder jpql = new StringBuilder();
		
		for (CotacaoItens cotacaoItem : listCotacaoFornecedores) {
			try {
				Compra compra = new Compra();
				compra.setDataDaCompra(Calendar.getInstance());
				compra.setFornecedor(cotacaoItem.getFornecedor());
				compra.setNumero(this.compraService.nextFieldRecord());
				compra.setCotacao(cotacao);
				compra.setSituacao(0);
				this.compraService.insert(compra);
			
				jpql.delete(0, jpql.length());
				jpql.append("  FROM " + new CotacaoItens().getClass().getName() +
							" WHERE idFornecedor = " + cotacaoItem.getFornecedor().getId().toString() +
							" AND idcotacao = " + cotacaoItem.getCotacao().getId() +
							"   AND situacao = 0");
	
				List<CotacaoItens> listCotacaoItens = this.cotacaoItensService.findByFields(jpql.toString());
				ArrayList<CompraItens> listCompraItens = new ArrayList<CompraItens>();
				
				for (CotacaoItens cotacaoItem2 : listCotacaoItens) {
					CompraItens compraItem = new CompraItens();
					compraItem.setCompra(compra);
					compraItem.setProduto(cotacaoItem2.getProduto());
					compraItem.setValor(cotacaoItem2.getValor());
					compraItem.setCotacaoItens(cotacaoItem2);
					compraItem.setQuantidade(cotacaoItem2.getQuantidade());
					listCompraItens.add(compraItem);
	
					cotacaoItem2.setSituacao(2);
				}
				cotacao.setSituacao(2);
			
				this.update(cotacao);
				this.compraItensService.insertAnyRecord(listCompraItens);
				this.cotacaoItensService.updateAnyRecord(listCotacaoItens);

			} catch (FieldsUnfilledException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean cancelCotacao(Cotacao cotacao) {
		try {
			String jpql = "  FROM " + new CotacaoItens().getClass().getName() + " WHERE idCotacao = " + cotacao.getId().toString() + "   AND situacao = 0";
			
			List<CotacaoItens> listCotacaoItens = this.getCotacaoItensService().findByFields(jpql);

			for (CotacaoItens cotacaoItens : listCotacaoItens) {
				cotacaoItens.setSituacao(1);
			}
			
			this.getCotacaoItensService().updateAnyRecord(listCotacaoItens);

			cotacao.setSituacao(1);
			this.update(cotacao);

			return true;
		} catch (FieldsUnfilledException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String prepareUpdateItem() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COTACAOITENS);
	}
	
	@Override
	public String prepareDetailItem() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COTACAOITENS);
	}
	
	@Override
	public void removeItem(CotacaoItens cotacaoItem) throws CannotDeleteException {
		this.cotacaoItensDao.remove(cotacaoItem);
	}
	
	@Override
	public Integer nextFieldRecord() {
		return this.cotacaoDao.nextFieldRecord();
	}
	
	public List<?> grupingRecords(Cotacao cotacao) {
		String jpql = "FROM " + new CotacaoItens().getClass().getName() + " WHERE idCotacao = " + cotacao.getId() + "   AND situacao  = 0" +
					  " GROUP BY idFornecedor";
		return this.cotacaoItensService.findByFields(jpql);
	}
	
	public ICotacaoDao getCotacaoDao() {
		return cotacaoDao;
	}

	public void setCotacaoDao(ICotacaoDao cotacaoDao) {
		this.cotacaoDao = cotacaoDao;
	}

	public ICotacaoItensDao getCotacaoItensDao() {
		return cotacaoItensDao;
	}

	public void setCotacaoItensDao(ICotacaoItensDao cotacaoItensDao) {
		this.cotacaoItensDao = cotacaoItensDao;
	}

	public ICotacaoItensService getCotacaoItensService() {
		return cotacaoItensService;
	}

	public void setCotacaoItensService(ICotacaoItensService cotacaoItensService) {
		this.cotacaoItensService = cotacaoItensService;
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
}
