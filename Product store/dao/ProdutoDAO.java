package br.com.unip.tcc.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IProdutoDao;
import br.com.unip.tcc.model.Estoque;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.vo.ProdutoVO;

@Repository("fornecedorDao")
public class ProdutoDAO extends GenericDao<Produto, ProdutoVO>  implements IProdutoDao, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoDAO() {}
	
	@Override
	public boolean insert(Produto entity) throws FieldsUnfilledException {
		if (entity.getDescricao().isEmpty()) entity.setDescricao(null);
		return super.insert(entity);
	}

	@Override
	public boolean update(Produto entity) throws FieldsUnfilledException {
		if (entity.getDescricao().isEmpty()) entity.setDescricao(null);
		return super.update(entity);
	}

	@Override
	public void remove(Produto entity) throws CannotDeleteException {
		super.remove(entity);
	}

	@Override
	public Produto findById(Integer id) {
		return super.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> findByFields(ProdutoVO entityVo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM " + new Produto().getClass().getName() + " produto WHERE 1=1");
		if (entityVo.getDescricao() != null) {
			if (!entityVo.getDescricao().isEmpty())
				hql.append(" AND UPPER(descricao) LIKE " + "'" + entityVo.getDescricao().toUpperCase() + "%'");
		}
		if (entityVo.getFornecedor() != null) {
			if (!entityVo.getFornecedor().isEmpty())
				hql.append(" AND UPPER(fornecedor) LIKE " + "'" + entityVo.getFornecedor().toUpperCase() + "%'");
		}
		if (entityVo.getMarca() != null) {
			if (!entityVo.getMarca().isEmpty())
				hql.append(" AND UPPER(marca) LIKE " + "'" + entityVo.getMarca().toUpperCase() + "%'");
		}
		if ((entityVo.getValorCompraInicial() != null) && (entityVo.getValorCompraFinal() != null)) {
			hql.append(" AND precoCompra BETWEEN " + entityVo.getValorCompraInicial().toString() + " AND "
												   + entityVo.getValorCompraFinal().toString());
		}
		if ((entityVo.getValorVendaInicial() != null) && (entityVo.getValorVendaFinal() != null)) {
			hql.append(" AND precoCompra BETWEEN " + entityVo.getValorVendaInicial().toString() + " AND "
											       + entityVo.getValorVendaFinal().toString());
		}
		if (entityVo.getEstoque() != null) {
			switch (entityVo.getEstoque()) {
				case 1:
					hql.append(" AND  (select sum(quantidade) from " + new Estoque().getClass().getName() +
							   " 	where idproduto = produto.id) > 0");
					break;
				case 2:
					hql.append(" AND ((select sum(quantidade) from " + new Estoque().getClass().getName() + " where idproduto = produto.id) <= 0)" +
							   "  OR  (select sum(quantidade) from " + new Estoque().getClass().getName() + " where idproduto = produto.id) IS NULL");
					break;
				default:
					break;
			}
		}
		
		hql.append(" ORDER BY id DESC");
		return (List<Produto>) super.findByFields(hql.toString());
	}

	@Override
	public boolean isRecordExists(Produto entity) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM " + new Produto().getClass().getName() + " WHERE descricao = '" + entity.getDescricao().toUpperCase() + "'" +
					                                              "   AND id <> " + entity.getId());
		return super.isExistsRecord(hql.toString());
	}

	public boolean insertAnyRecord(List<Produto> listRecord) {
		return false;
	}

	public boolean updateAnyRecord(List<Produto> listRecord) {
		return false;
	}
}
