package br.com.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICompraDao;
import br.com.unip.tcc.interfaces.ICompraService;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.system.Screen;
import br.com.unip.tcc.util.FacesUtil;
import br.com.unip.tcc.vo.CompraVO;

@Service("compraService")
public class CompraService implements ICompraService {

	@Autowired
	private ICompraDao compraDao;
	
	@Override
	public String newRecord() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COMPRA);
	}

	@Override
	public boolean insert(Compra entity) throws FieldsUnfilledException {
		return this.compraDao.insert(entity);
	}

	@Override
	public boolean update(Compra entity) throws FieldsUnfilledException {
		return this.compraDao.update(entity);
	}

	@Override
	public void remove(Compra entity) throws CannotDeleteException {
		this.compraDao.remove(entity);
	}

	@Override
	public String cancel() {
		return FacesUtil.facesRedirectUrl(Screen.INCLUIR_COMPRA);
	}

	@Override
	public String prepareUpdate() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COMPRA);
	}

	@Override
	public String prepareDetail() {
		return FacesUtil.facesRedirectUrl(Screen.INCLUIR_COMPRA);
	}

	@Override
	public List<Compra> findByFields(CompraVO compraVO) {
		return this.compraDao.findByFields(compraVO);
	}

	@Override
	public boolean isRecordExists(Compra compra) {
		return this.compraDao.isRecordExists(compra);
	}

	@Override
	public Compra findById(Integer id) {
		return this.compraDao.findById(id);
	}

	@Override
	public String prepareUpdateItem() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COMPRAITENS);
	}

	@Override
	public Integer nextFieldRecord() {
		return this.compraDao.nextFieldRecord();
	}
	
	@Override
	public void removeItem(CompraItens compraItem) {
	}
	
	@Override
	public void confirmCompra(Compra compra) {
		this.compraDao.confirmCompra(compra);
	}
}
