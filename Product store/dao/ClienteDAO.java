package br.com.unip.tcc.dao;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IClienteDAO;
import br.com.unip.tcc.model.Cliente;
import br.com.unip.tcc.vo.ClienteVO;

@Repository("clienteDao")
public class ClienteDAO extends GenericDao<Cliente, ClienteVO> implements IClienteDAO, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@PersistenceContext
	//private static EntityManager entityManagerA;

	public ClienteDAO() {}
	
	public ClienteDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	//@Transactional
	public boolean insert(Cliente entity) throws FieldsUnfilledException {
		if (entity.getNome().isEmpty()) entity.setNome(null);
		if (entity.getRg().isEmpty()) entity.setRg(null);
		if (entity.getCpf().isEmpty()) entity.setCpf(null);
		if (entity.getSexo().isEmpty()) entity.setSexo(null);
		return super.insert(entity);
	}
	@Override
	//@Transactional
	public boolean update(Cliente entity) throws FieldsUnfilledException {
		if (entity.getNome().isEmpty()) entity.setNome(null);
		if (entity.getRg().isEmpty()) entity.setRg(null);
		if (entity.getCpf().isEmpty()) entity.setCpf(null);
		if (entity.getSexo().isEmpty()) entity.setSexo(null);
		return super.update(entity);
	}
	@Override
	@Transactional
	public void remove(Cliente entity) throws CannotDeleteException {
		 super.remove(entity);
	}
	@Override
	public Cliente findById(Integer id) {
		return super.findById(id);
	}
	@Override
	public List<Cliente> findByFields(ClienteVO entityVo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM " + new Cliente().getClass().getName() + " WHERE 1=1");
		
		if (entityVo.getNome() != null) {
			if (!entityVo.getNome().isEmpty())
				hql.append(" AND UPPER(nome) LIKE " + "'" + entityVo.getNome().toUpperCase() + "%'");
		}
		
		if (entityVo.getSexo() != null) {
			if (!entityVo.getSexo().isEmpty() && !entityVo.getSexo().equals("D"))
				hql.append(" AND sexo =  " + "'" + entityVo.getSexo() + "'");
		}
		
		if (entityVo.getRg() != null) {
			if (!entityVo.getRg().isEmpty())
				hql.append(" AND rg =  " + "'" + entityVo.getRg() + "'");
		}
		
		if (entityVo.getCpf() != null) {
			if (!entityVo.getCpf().isEmpty())
				hql.append(" AND cpf =  " + "'" + entityVo.getCpf() + "'");
		}
		
		if (entityVo.getDataNascimentoInicial() != null) {
			Date dateInicial = (Date) entityVo.getDataNascimentoInicial().getTime();
			Date dateFinal = (Date) entityVo.getDataNascimentoFinal().getTime();
			SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
			
			String dataNascimentoInicial = spf.format(dateInicial);
			String dataNascimentoFinal = spf.format(dateFinal);
			
			hql.append(" AND dataNascimento between  " + "'" + dataNascimentoInicial + "'" +
					   " AND " + "'" + dataNascimentoFinal + "'");
		}
		
		hql.append(" ORDER BY id DESC");
		
		return (List<Cliente>) super.findByFields(hql.toString());
	}
	@Override
	public boolean isRecordExists(Cliente entity) {
		String hql = "FROM " + new Cliente().getClass().getName() + " WHERE cpf = '" + entity.getCpf().toUpperCase() + "'" +
 					                                                "   AND id <> " + entity.getId();
		return false; //super.isExistsRecord(hql);
	}
}
