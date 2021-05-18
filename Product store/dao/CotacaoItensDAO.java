package br.com.unip.tcc.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICotacaoItensDao;
import br.com.unip.tcc.model.Cotacao;
import br.com.unip.tcc.model.CotacaoItens;
import br.com.unip.tcc.system.Screen;
import br.com.unip.tcc.util.FacesUtil;
import br.com.unip.tcc.vo.CotacaoItensVO;

@Repository("cotacaoItensDAO")
public class CotacaoItensDAO extends GenericDao<CotacaoItens, CotacaoItensVO> implements ICotacaoItensDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CotacaoItensDAO() {}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean insert(CotacaoItens entity) throws FieldsUnfilledException {
		return super.insert(entity);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean update(CotacaoItens entity) throws FieldsUnfilledException {
		return super.update(entity);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void remove(CotacaoItens entity) throws CannotDeleteException {
		super.remove(entity);
	}

	@Override
	public CotacaoItens findById(Integer id) {
		return super.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CotacaoItens> findByFields(CotacaoItensVO entityVo) {
		String hql = " FROM " + new CotacaoItens().getClass().getName() + " WHERE idcotacao = " + entityVo.getIdCotacao() + " ORDER BY id";
		return (List<CotacaoItens>) super.findByFields(hql);
	}

	@Override
	public List<?> findByFields(String jpql) {
		return super.findByFields(jpql);
	}
	
	@Override
	public boolean isRecordExists(CotacaoItens entity) {
		String hql = "FROM " + entity.getClass().getName() +
				     " WHERE idcotacao    = " + entity.getCotacao().getId() +
				     "   AND idproduto    = " + entity.getProduto().getId() +
				     "   AND idfornecedor = " + entity.getFornecedor().getId() +
				     "   AND id <> " + entity.getId();
		return super.isExistsRecord(hql);
	}

	@Override
	public CotacaoItens findByIdCotacao(Integer id) {
		new CotacaoDAO().findById(id);
		return null;
	}
	
	@Override
	public String cancel(Cotacao entity) {
		FacesUtil.createSession("cotacaoId", entity.getId());
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_COTACAO);
	}
	
	@Override	
	public Integer getCotacaoItensAberto(Cotacao cotacao) {
		String jpql = " SELECT count(id) FROM " +
					  new CotacaoItens().getClass().getName() +
					   " WHERE idcotacao = " + cotacao.getId() +
			    	   "   AND situacao = 0";
		
		@SuppressWarnings("unchecked")
		List<Long> quantidadeLong = (List<Long>) super.findByFields(jpql);
		return (int) ((quantidadeLong.get(0) == null) ? 0 : quantidadeLong.get(0));
	}
	
	@Override
	public Integer getCotacaoItensConfirmado(Cotacao cotacao) {
		String jpql = " SELECT count(id) FROM " +
				  new CotacaoItens().getClass().getName() +
				   " WHERE idcotacao = " + cotacao.getId() +
		    	   "   AND situacao = 2";
	
		@SuppressWarnings("unchecked")
		List<Long> quantidadeLong = (List<Long>) super.findByFields(jpql);
		return (int) ((quantidadeLong.get(0) == null) ? 0 : quantidadeLong.get(0));
	}
	
	@Override
	public Integer getCotacaoItensCancelado(Cotacao cotacao) {
		String jpql = " SELECT count(id) FROM " +
				  new CotacaoItens().getClass().getName() +
				   " WHERE idcotacao = " + cotacao.getId() +
		    	   "   AND situacao = 1";
	
		@SuppressWarnings("unchecked")
		List<Long> quantidadeLong = (List<Long>) super.findByFields(jpql);
		return (int) ((quantidadeLong.get(0) == null) ? 0 : quantidadeLong.get(0));
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean insertAnyRecord(List<CotacaoItens> listRecord) {
		return super.insertAnyRecord(listRecord);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean updateAnyRecord(List<CotacaoItens> listRecord) {
		return super.updateAnyRecord(listRecord);
	}
	
}
