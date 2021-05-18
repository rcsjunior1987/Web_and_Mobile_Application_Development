package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.vo.CotacaoVO;

public interface ICotacaoDao extends IGenericDao<Cotacao, CotacaoVO> {
	boolean insert(Cotacao entity) throws FieldsUnfilledException;
	@Override
	boolean update(Cotacao entity) throws FieldsUnfilledException;
	@Override
	void remove(Cotacao entity) throws CannotDeleteException;
	@Override
	List<Cotacao> findByFields(CotacaoVO entityVo);
	@Override
	boolean isRecordExists(Cotacao entity);
	@Override
	boolean insertAnyRecord(List<Cotacao> listRecord);
	@Override
	boolean updateAnyRecord(List<Cotacao> listRecord);
	@SuppressWarnings("rawtypes")
	List grupingRecords(Cotacao cotacao);
	Integer nextFieldRecord();
}
