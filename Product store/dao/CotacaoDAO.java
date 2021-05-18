package br.com.unip.tcc.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICotacaoDao;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.model.CotacaoItens;
import br.com.unip.tcc.vo.CotacaoVO;

@Repository("cotacaoDAO")
public class CotacaoDAO extends GenericDao<Cotacao, CotacaoVO> implements ICotacaoDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CotacaoDAO() {
	}

	@Override
	//@Transactional(rollbackFor=Exception.class)
	public boolean insert(Cotacao entity) throws FieldsUnfilledException {
		super.insert(entity);
		
		System.out.println("id da cotacao = " + entity.getId());
		
		return true;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean update(Cotacao entity) throws FieldsUnfilledException {
		return super.update(entity);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void remove(Cotacao entity) throws CannotDeleteException {
		super.remove(entity);
	}

	@Override
	public Cotacao findById(Integer id) {
		return super.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cotacao> findByFields(CotacaoVO entityVo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM " + new Cotacao().getClass().getName() + " WHERE 1=1");

		if (entityVo.getNumero() != null) {
			hql.append(" AND numero = " + entityVo.getNumero());
		}

		if (entityVo.getSituacao() != null) {
			hql.append(" AND situacao = " + entityVo.getSituacao());
		}

		if (entityVo.getIdCliente() != null) {
			hql.append(" AND idcliente = " + entityVo.getIdCliente());
		}

		hql.append(" ORDER BY id DESC");

		return (List<Cotacao>) super.findByFields(hql.toString());
	}

	@Override
	public boolean isRecordExists(Cotacao entity) {
		String hql = "FROM " + entity.getClass().getName() + " WHERE numero = " + entity.getNumero() + "" + "   AND id <> " + entity.getId();
		return super.isExistsRecord(hql);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List grupingRecords(Cotacao cotacao) {
		String hql = "FROM " + new CotacaoItens().getClass().getName() + " WHERE idCotacao = " + cotacao.getId() + "   AND situacao  = 0"
				+ " GROUP BY idFornecedor";
		return super.findByFields(hql.toString());
	}
	
	@Override
	public Integer nextFieldRecord() {
		Cotacao cotacaoFind = new Cotacao();
		String jpql = " select max(c.numero) FROM " + cotacaoFind.getClass().getName() + " c ";
		@SuppressWarnings("unchecked")
		List<Integer> numero = (List<Integer>) super.findByFields(jpql);
		return numero.get(0) == null ? 1 : numero.get(0) + 1;
	}
	
	
}