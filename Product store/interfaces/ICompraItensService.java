package br.com.unip.tcc.interfaces;

import java.util.List;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.vo.CompraItensVO;

public interface ICompraItensService {
	String newRecord();
	boolean insert(CompraItens entity) throws FieldsUnfilledException;
	boolean update(CompraItens entity) throws FieldsUnfilledException;
	String remove(CompraItens entity) throws CannotDeleteException;
	String cancel();
	CompraItens findById();
	List<CompraItens> findByFields(CompraItensVO compraItensVO);
	boolean isRecordExists(CompraItens cotacaoItens);
	boolean insertAnyRecord(List<CompraItens> listRecord);
	boolean updateAnyRecord(List<CompraItens> listRecord);
}
