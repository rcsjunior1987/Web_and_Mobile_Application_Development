package br.com.unip.tcc.dao;
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICompraItensDao;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.system.Screen;
import br.com.unip.tcc.util.FacesUtil;
import br.com.unip.tcc.vo.CompraItensVO;

@Repository("compraItensDAO")
public class CompraItensDAO extends GenericDao<CompraItens, CompraItensVO>  implements ICompraItensDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean insert(CompraItens entity) throws FieldsUnfilledException {
		return super.insert(entity);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean update(CompraItens entity) throws FieldsUnfilledException {
		return super.update(entity);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void remove(CompraItens entity) throws CannotDeleteException {
		super.remove(entity);
	}

	@Override
	public CompraItens findById(Integer id) {
		return super.findById(id);
	}

	@Override
	public List<CompraItens> findByFields(CompraItensVO entityVo) {
		return null;
	}

	@Override
	public boolean isRecordExists(CompraItens entity) {
		return false;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean insertAnyRecord(List<CompraItens> listRecord) {
		return super.insertAnyRecord(listRecord);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean updateAnyRecord(List<CompraItens> listRecord) {
		return super.updateAnyRecord(listRecord);
	}

	@Override
	public CompraItens findByIdCompra(Integer id) {
		return new CompraItensDAO().findById(id);
	}
	
	@Override
	public String cancel(Compra entity) {
		FacesUtil.createSession("detailCompraId", entity.getId());
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COMPRA);
	}
}