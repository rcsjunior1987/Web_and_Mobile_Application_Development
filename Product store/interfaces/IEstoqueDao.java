package br.com.unip.tcc.interfaces;

import java.util.List;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Estoque;
import br.com.unip.tcc.vo.EstoqueVO;

public interface IEstoqueDao extends IGenericDao<Estoque, EstoqueVO> {
	@Override
	boolean insert(Estoque entity) throws FieldsUnfilledException;
	@Override
	boolean update(Estoque entity) throws FieldsUnfilledException;
	@Override
	void remove(Estoque entity) throws CannotDeleteException;
	@Override
	Estoque findById(Integer id);
	@Override
	List<Estoque> findByFields(EstoqueVO entityVo);
	@Override
	boolean isRecordExists(Estoque entity);
	@Override
	boolean insertAnyRecord(List<Estoque> listRecord);
	@Override
	boolean updateAnyRecord(List<Estoque> listRecord);
}
