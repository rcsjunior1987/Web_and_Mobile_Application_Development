package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.vo.CompraVO;

public interface ICompraService {
	String newRecord();
	boolean insert(Compra entity) throws FieldsUnfilledException;
	boolean update(Compra entity) throws FieldsUnfilledException;
	void remove(Compra entity) throws CannotDeleteException;
	String cancel();
	String prepareUpdate();
	String prepareDetail();
	Compra findById(Integer id);
	List<Compra> findByFields(CompraVO compraVO);
	boolean isRecordExists(Compra compra);
	String prepareUpdateItem();
	Integer nextFieldRecord();
	void removeItem(CompraItens compraItem) throws CannotDeleteException;
	void confirmCompra(Compra compra);
}
