package br.com.unip.tcc.dao;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IEstoqueDao;
import br.com.unip.tcc.model.Estoque;
import br.com.unip.tcc.vo.EstoqueVO;

@Service("estoqueDAO")
public class EstoqueDAO extends GenericDao<Estoque, EstoqueVO> implements IEstoqueDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstoqueDAO() {}
	
	@Override
	public boolean insert(Estoque entity) throws FieldsUnfilledException {
		return super.insert(entity);
	}

	@Override
	public boolean update(Estoque entity) throws FieldsUnfilledException {
		return false;
	}

	@Override
	public void remove(Estoque entity) throws CannotDeleteException {}

	@Override
	public Estoque findById(Integer id) {
		return null;
	}

	@Override
	public List<Estoque> findByFields(EstoqueVO entityVo) {
		return null;
	}

	@Override
	public boolean insertAnyRecord(List<Estoque> listRecord) {
		return false;
	}

	@Override
	public boolean updateAnyRecord(List<Estoque> listRecord) {
		return false;
	}

	@Override
	public boolean isRecordExists(Estoque entity) {
		return false;
	}
}
