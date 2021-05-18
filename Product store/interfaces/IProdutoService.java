package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.vo.ProdutoVO;

public interface IProdutoService {
	String newRecord(ProdutoVO produtoVo);
	boolean insert(Produto entity) throws FieldsUnfilledException;
	boolean update(Produto entity) throws FieldsUnfilledException;
	void remove(Produto entity) throws CannotDeleteException;
	String cancel(Produto entity);
	String prepareUpdate(Produto produto);
	String prepareDetail(Produto produto);
	Produto findById(Integer id);
	List<Produto> findByFields(ProdutoVO produtoVO);
	boolean isRecordExists(Produto entity);
}
