package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.vo.ProdutoVO;
public interface IProdutoDao extends IGenericDao<Produto, ProdutoVO> {
	@Override
	boolean insert(Produto entity) throws FieldsUnfilledException;
	@Override
	boolean update(Produto entity) throws FieldsUnfilledException;
	@Override
	void remove(Produto entity) throws CannotDeleteException;
	@Override
	Produto findById(Integer id);
	@Override
	List<Produto> findByFields(ProdutoVO entityVo);
	@Override
	boolean isRecordExists(Produto entity);
	@Override
	boolean insertAnyRecord(List<Produto> listRecord);
	@Override
	boolean updateAnyRecord(List<Produto> listRecord);
}
