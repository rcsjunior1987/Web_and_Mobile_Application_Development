package br.com.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IFornecedorDao;
import br.com.unip.tcc.interfaces.IFornecedorService;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.system.Screen;
import br.com.unip.tcc.util.FacesUtil;
import br.com.unip.tcc.vo.FornecedorVO;

@Service("fornecedorService")
public class FornecedorService implements IFornecedorService {
	
	@Autowired
	private IFornecedorDao fornecedorDao;
	
	@Override
	public String newRecord(FornecedorVO fornecedorVO) {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_FORNECEDOR);
	}
	@Override
	public boolean insert(Fornecedor entity) throws FieldsUnfilledException {
		return this.fornecedorDao.insert(entity);
	}

	@Override
	public boolean update(Fornecedor entity) throws FieldsUnfilledException {
		return this.fornecedorDao.update(entity);
	}

	@Override
	public void remove(Fornecedor entity) throws CannotDeleteException {
		this.fornecedorDao.remove(entity);
	}

	@Override
	public String cancel(Fornecedor entity) {
		return FacesUtil.facesRedirectUrl(Screen.INCLUIR_FORNECEDOR);
	}

	@Override
	public String prepareUpdate(Fornecedor fornecedor) {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_FORNECEDOR);
	}

	@Override
	public String prepareDetail(Fornecedor fornecedor) {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_FORNECEDOR);
	}

	@Override
	public Fornecedor findById() {
		Integer id = null;
		return this.fornecedorDao.findById(id);
	}

	@Override
	public List<Fornecedor> findByFields(FornecedorVO fornecedorVO) {
		return this.fornecedorDao.findByFields(fornecedorVO);
	}

	@Override
	public boolean isRecordExists(Fornecedor fornecedor) {
		return this.fornecedorDao.isRecordExists(fornecedor);
	}
}
