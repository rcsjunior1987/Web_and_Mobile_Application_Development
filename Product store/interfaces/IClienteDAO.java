package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Cliente;
import br.com.unip.tcc.vo.ClienteVO;
public interface IClienteDAO extends IGenericDao<Cliente, ClienteVO> {
	@Override
	boolean insert(Cliente entity) throws FieldsUnfilledException;
	@Override
	boolean update(Cliente entity) throws FieldsUnfilledException;
	@Override
	void remove(Cliente entity) throws CannotDeleteException;
	@Override
	Cliente findById(Integer id);
	@Override
	List<Cliente> findByFields(ClienteVO entityVo);
	@Override
	boolean isRecordExists(Cliente entity);
	@Override
	boolean insertAnyRecord(List<Cliente> listRecord);
	@Override
	boolean updateAnyRecord(List<Cliente> listRecord);
}
