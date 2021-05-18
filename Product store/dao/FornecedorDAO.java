package br.com.unip.tcc.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IFornecedorDao;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.vo.FornecedorVO;

@Repository("fornecedorDAO")
public class FornecedorDAO extends GenericDao<Fornecedor, FornecedorVO>  implements IFornecedorDao, Serializable {
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {}
	
	@Override
	public boolean insert(Fornecedor entity) throws FieldsUnfilledException {
		return super.insert(entity);
	}

	@Override
	public boolean update(Fornecedor entity) throws FieldsUnfilledException {
		return super.update(entity);
	}

	@Override
	public void remove(Fornecedor entity) throws CannotDeleteException {
		super.remove(entity);
	}
	
	@Override
	public Fornecedor findById(Integer id) {
		return super.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> findByFields(FornecedorVO entityVo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM " + new Fornecedor().getClass().getName() + " WHERE 1=1");
		
		if (entityVo.getNomeFantasia() != null) {
			if (!entityVo.getNomeFantasia().isEmpty())
				hql.append(" AND UPPER(nomeFantasia) LIKE " + "'" + entityVo.getNomeFantasia().toUpperCase() + "%'");
		}
		
		if (entityVo.getRazaoSocial() != null) {
			if (!entityVo.getRazaoSocial().isEmpty())
				hql.append(" AND UPPER(razaoSocial) LIKE " + "'" + entityVo.getRazaoSocial().toUpperCase() + "%'");
		}
		
		hql.append(" ORDER BY id DESC");
		return (List<Fornecedor>) super.findByFields(hql.toString());
	}

	@Override
	public boolean isRecordExists(Fornecedor entity) {
		String hql = "FROM " + new Fornecedor().getClass().getName() + " WHERE razaoSocial = '" + entity.getRazaoSocial().toUpperCase() + "'";
		return super.isExistsRecord(hql);
	}
}
