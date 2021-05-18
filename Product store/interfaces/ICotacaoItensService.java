package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.model.CotacaoItens;
import br.com.unip.tcc.vo.CotacaoItensVO;

public interface ICotacaoItensService {
	String newRecord();
	boolean insert(CotacaoItens entity) throws FieldsUnfilledException;
	boolean update(CotacaoItens entity) throws FieldsUnfilledException;
	String remove(CotacaoItens entity) throws CannotDeleteException;
	String cancel();
	CotacaoItens findById(Integer integer);
	List<CotacaoItens> findByFields(CotacaoItensVO cotacaoItensVO);
	List<CotacaoItens> findByFields(String jpql);
	boolean isRecordExists(CotacaoItens cotacaoItens);
	boolean newCompra(CotacaoItens cotacaoItem);
	boolean cancelItem(CotacaoItens cotacaoItem);
	Integer getCotacaoItensAberto(Cotacao cotacao);
	Integer getCotacaoItensConfirmado(Cotacao cotacao);
	Integer getCotacaoItensCancelado(Cotacao cotacao);
	boolean insertAnyRecord(List<CotacaoItens> listRecord);
	boolean updateAnyRecord(List<CotacaoItens> listRecord);
	Integer getSituacao(Cotacao cotacao);
}
