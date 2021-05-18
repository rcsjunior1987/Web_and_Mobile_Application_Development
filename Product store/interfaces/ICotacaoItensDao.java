package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.model.CotacaoItens;
import br.com.unip.tcc.vo.CotacaoItensVO;

public interface ICotacaoItensDao extends IGenericDao<CotacaoItens, CotacaoItensVO> {
	@Override
	boolean insert(CotacaoItens entity) throws FieldsUnfilledException;
	@Override
	boolean update(CotacaoItens entity) throws FieldsUnfilledException;
	@Override
	void remove(CotacaoItens entity) throws CannotDeleteException;
	@Override
	CotacaoItens findById(Integer id);
	@Override
	List<CotacaoItens> findByFields(CotacaoItensVO entityVo);
	@Override
	boolean isRecordExists(CotacaoItens entity);
	@Override
	boolean insertAnyRecord(List<CotacaoItens> listRecord);
	@Override
	boolean updateAnyRecord(List<CotacaoItens> listRecord);
	CotacaoItens findByIdCotacao(Integer id);
	String cancel(Cotacao entity);
	Integer getCotacaoItensAberto(Cotacao cotacao);
	Integer getCotacaoItensConfirmado(Cotacao cotacao);
	Integer getCotacaoItensCancelado(Cotacao cotacao);
}
