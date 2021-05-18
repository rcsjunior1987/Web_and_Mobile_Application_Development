package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.vo.CompraItensVO;

public interface ICompraItensDao extends IGenericDao<CompraItens, CompraItensVO> {
	@Override
	boolean insert(CompraItens entity) throws FieldsUnfilledException;
	@Override
	boolean update(CompraItens entity) throws FieldsUnfilledException;
	@Override
	void remove(CompraItens entity) throws CannotDeleteException;
	@Override
	CompraItens findById(Integer id);
	@Override
	List<CompraItens> findByFields(CompraItensVO entityVo);
	@Override
	boolean isRecordExists(CompraItens entity);
	@Override
	boolean insertAnyRecord(List<CompraItens> listRecord);
	@Override
	boolean updateAnyRecord(List<CompraItens> listRecord);
	CompraItens findByIdCompra(Integer id);
	String cancel(Compra entity);
}