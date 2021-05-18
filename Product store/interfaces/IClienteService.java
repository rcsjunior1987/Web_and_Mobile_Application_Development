package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Cliente;
import br.com.unip.tcc.vo.ClienteVO;

public interface IClienteService {
	String newRecord(ClienteVO clienteVo);
	boolean insert(Cliente entity) throws FieldsUnfilledException;
	boolean update(Cliente entity) throws FieldsUnfilledException;
	void remove(Cliente entity) throws CannotDeleteException;
	String cancel(Cliente entity);
	String prepareUpdate(Cliente cliente);
	String prepareDetail(Cliente cliente);
	Cliente findById();
	List<Cliente> findByFields(ClienteVO clienteVO);
	boolean isRecordExists(Cliente entity);
	boolean isCpfExists(Cliente entity);
}
