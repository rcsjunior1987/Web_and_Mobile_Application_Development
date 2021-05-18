package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.vo.CompraVO;

public interface ICompraDao extends IGenericDao<Compra, CompraVO> {
	@Override
	boolean insert(Compra entity) throws FieldsUnfilledException;
	@Override
	boolean update(Compra entity) throws FieldsUnfilledException;
	@Override
	void remove(Compra entity) throws CannotDeleteException;
	@Override
	Compra findById(Integer id);
	@Override
	List<Compra> findByFields(CompraVO entityVo);
	@Override
	boolean isRecordExists(Compra entity);
	@Override
	boolean insertAnyRecord(List<Compra> listRecord);
	@Override
	boolean updateAnyRecord(List<Compra> listRecord);
	Integer nextFieldRecord();
	boolean confirmCompra(Compra entity);
}
