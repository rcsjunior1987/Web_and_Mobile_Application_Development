package br.com.unip.tcc.interfaces;

import java.util.List;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.vo.FornecedorVO;

public interface IFornecedorDao extends IGenericDao<Fornecedor, FornecedorVO> {
	@Override
	boolean insert(Fornecedor entity) throws FieldsUnfilledException;
	@Override
	boolean update(Fornecedor entity) throws FieldsUnfilledException;
	@Override
	void remove(Fornecedor entity) throws CannotDeleteException;
	@Override
	Fornecedor findById(Integer id);
	@Override
	List<Fornecedor> findByFields(FornecedorVO entityVo);
	@Override
	boolean isRecordExists(Fornecedor entity);
	@Override
	boolean insertAnyRecord(List<Fornecedor> listRecord);
	@Override
	boolean updateAnyRecord(List<Fornecedor> listRecord);
}
