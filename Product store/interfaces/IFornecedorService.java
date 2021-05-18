package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.vo.FornecedorVO;

public interface IFornecedorService {
	String newRecord(FornecedorVO fornecedorVO);
	boolean insert(Fornecedor entity) throws FieldsUnfilledException;
	boolean update(Fornecedor entity) throws FieldsUnfilledException;
	void remove(Fornecedor entity) throws CannotDeleteException;
	String cancel(Fornecedor entity);
	String prepareUpdate(Fornecedor fornecedor);
	String prepareDetail(Fornecedor fornecedor);
	Fornecedor findById();
	List<Fornecedor> findByFields(FornecedorVO fornecedorVO);
	boolean isRecordExists(Fornecedor fornecedor);
}
