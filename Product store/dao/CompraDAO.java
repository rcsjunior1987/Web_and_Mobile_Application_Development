package br.com.unip.tcc.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.ICompraDao;
import br.com.unip.tcc.model.Cliente;
import br.com.unip.tcc.model.Compra;
import br.com.unip.tcc.model.CompraItens;
import br.com.unip.tcc.model.Estoque;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.vo.CompraVO;

@Repository("compraDAO")
public class CompraDAO extends GenericDao<Compra, CompraVO> implements ICompraDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CompraDAO() {}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean insert(Compra entity) throws FieldsUnfilledException {
		return super.insert(entity);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean update(Compra entity) throws FieldsUnfilledException {
		return super.update(entity);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void remove(Compra entity) throws CannotDeleteException {
		super.remove(entity);
	}

	@Override
	public Compra findById(Integer id) {
		String hql = " FROM " + new Compra().getClass().getName() + " WHERE id = " + id;
		Compra compra = new Compra();
		//compra = super.findByFields(hql).get(0);
		
		String hqlItens = new String();
		hqlItens = " FROM " + new CompraItens().getClass().getName() + " WHERE idcompra = " + compra.getId();
		//compra.setListCompraItens(new CompraItensDAO().findByFields(hqlItens));
		return compra;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compra> findByFields(CompraVO entityVo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM " + new Compra().getClass().getName() + " WHERE 1=1");
		
		if (entityVo.getNumero() != null) {
			hql.append(" AND numero = " + entityVo.getNumero());
		}
		
		if (entityVo.getSituacao() != null) {
			hql.append(" AND situacao = " + entityVo.getSituacao());
		}
		
		if (entityVo.getIdFornecedor() != null) {
			hql.append(" AND idfornecedor = " + entityVo.getIdFornecedor());
		}
		
		hql.append(" ORDER BY id DESC");
		
		return (List<Compra>) super.findByFields(hql.toString());
	}

	@Override
	public boolean isRecordExists(Compra entity) {
		String hql = "FROM " + entity.getClass().getName() + " WHERE numero = " + entity.getNumero() + "" +
					                                         "   AND id <> " + entity.getId();
		return super.isExistsRecord(hql);
	}

	@Override
	public boolean confirmCompra(Compra entity) {
		try {
			if (entity.getListCompraItens().size() == 0) {
				FacesMessageUtil.showMessageInfo(Messages.M22);
				return false;
			}
			
			List<CompraItens> listCompraItens = entity.getListCompraItens();	

			ArrayList<Estoque> listEstoque = new ArrayList<Estoque>();
	
			if (listCompraItens != null) {
				for (CompraItens compraItem : listCompraItens) {
					Estoque estoque = new Estoque();
					estoque.setProduto(compraItem.getProduto());
					estoque.setQuantidade(compraItem.getQuantidade());
					listEstoque.add(estoque);
				}
				new EstoqueDAO().insertAnyRecord(listEstoque);
				entity.setSituacao(2);
		
				new CompraDAO().update(entity);
				return true;
			}
		} catch (FieldsUnfilledException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public Integer nextFieldRecord() {
		Compra compraFind = new Compra();
		String jpql = " select max(c.numero) FROM " + compraFind.getClass().getName() + " c ";
		@SuppressWarnings("unchecked")
		List<Integer> numero = (List<Integer>) super.findByFields(jpql);
		return numero.get(0) == null ? 1 : numero.get(0) + 1;
	}
	
}
