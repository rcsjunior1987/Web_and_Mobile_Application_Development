package br.com.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICompraItensDao;
import br.com.unip.tcc.interfaces.ICompraItensService;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.system.Screen;
import br.com.unip.tcc.util.FacesUtil;
import br.com.unip.tcc.vo.CompraItensVO;

@Service("compraItensService")
public class CompraItensService implements ICompraItensService {

	@Autowired
	private ICompraItensDao compraItensDao;
	
	@Override
	public String newRecord() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COMPRAITENS);
	}

	@Override
	public boolean insert(CompraItens entity) throws FieldsUnfilledException {
		return this.compraItensDao.insert(entity);
	}

	@Override
	public boolean update(CompraItens entity) throws FieldsUnfilledException {
		return this.compraItensDao.update(entity);
	}

	@Override
	public String remove(CompraItens entity) throws CannotDeleteException {
		this.compraItensDao.remove(entity);
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COMPRA);
	}

	@Override
	public String cancel() {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COMPRA);
	}

	@Override
	public CompraItens findById() {
		Integer id = null;
		return this.compraItensDao.findById(id);
	}

	@Override
	public List<CompraItens> findByFields(CompraItensVO compraItensVO) {
		return this.compraItensDao.findByFields(compraItensVO);
	}

	@Override
	public boolean isRecordExists(CompraItens cotacaoItens) {
		return this.compraItensDao.isRecordExists(cotacaoItens);
	}

	@Override
	public boolean insertAnyRecord(List<CompraItens> listRecord) {
		return this.compraItensDao.insertAnyRecord(listRecord);
	}
	
	@Override
	public boolean updateAnyRecord(List<CompraItens> listRecord) {
		return this.compraItensDao.updateAnyRecord(listRecord);
	}
	
	public ICompraItensDao getCompraItensDao() {
		return compraItensDao;
	}

	public void setCompraItensDao(ICompraItensDao compraItensDao) {
		this.compraItensDao = compraItensDao;
	}
}
