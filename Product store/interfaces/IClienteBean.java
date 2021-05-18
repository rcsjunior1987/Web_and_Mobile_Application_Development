package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Cliente;
import br.com.unip.tcc.service.ClienteService;
import br.com.unip.tcc.vo.ClienteVO;

public interface IClienteBean {
	List<Cliente> getListAll();
	void setListAll(List<Cliente> listAll);
	Cliente getCliente();
	void setCliente(Cliente cliente);
	boolean isDetail();
	void setDetail(boolean detail);
	ClienteVO getClienteVo();
	void setClienteVo(ClienteVO clienteVo);
	String newRecord();
	void save() throws FieldsUnfilledException;
	void remove() throws CannotDeleteException;
	String prepareUpdate(Cliente cliente);
	String prepareDetail(Cliente cliente);
	String cancel();
	void findByFields();
	ClienteService getClienteService();
	void setClienteService(ClienteService clienteService);
}
