package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.model.CotacaoItens;
import br.com.unip.tcc.vo.CotacaoVO;

public interface ICotacaoService {
	String newRecord();
	boolean insert(Cotacao entity) throws FieldsUnfilledException;
	boolean update(Cotacao entity) throws FieldsUnfilledException;
	void remove(Cotacao entity) throws CannotDeleteException;
	String cancel();
	String prepareUpdate();
	String prepareDetail();
	Cotacao findById(Integer id);
	List<Cotacao> findByFields(CotacaoVO cotacaoVO);
	boolean isRecordExists(Cotacao cotacao);
	boolean newCompra(Cotacao cotacao);
	boolean cancelCotacao(Cotacao cotacao);
	String prepareUpdateItem();
	String prepareDetailItem();
	void removeItem(CotacaoItens cotacaoItem) throws CannotDeleteException;
	Integer nextFieldRecord();
}
