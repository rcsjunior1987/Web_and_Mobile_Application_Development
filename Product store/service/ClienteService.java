package br.com.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IClienteDAO;
import br.com.unip.tcc.interfaces.IClienteService;
import br.com.unip.tcc.model.Cliente;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.system.Screen;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.util.FacesUtil;
import br.com.unip.tcc.validator.CPFValidator;
import br.com.unip.tcc.vo.ClienteVO;

@Service("clinteService")
public class ClienteService implements IClienteService {
	
	@Autowired
	private IClienteDAO clienteDao;
	
	@Override
	public String newRecord(ClienteVO clienteVo) {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_CLIENTE);
	}
	@Override
	public boolean insert(Cliente entity) throws FieldsUnfilledException {
		if (!CPFValidator.isValid(entity.getCpf())) {
			FacesMessageUtil.showMessageError(Messages.M17);
			return false;
		}
		return this.clienteDao.insert(entity);
	}
	@Override
	public boolean update(Cliente entity) throws FieldsUnfilledException {
		if (!CPFValidator.isValid(entity.getCpf())) {
			FacesMessageUtil.showMessageError(Messages.M17);
			return false;
		}
		return this.clienteDao.update(entity);
	}
	@Override
	public void remove(Cliente entity) throws CannotDeleteException {
		this.clienteDao.remove(entity);
	}
	@Override
	public String cancel(Cliente entity) {
		return FacesUtil.facesRedirectUrl(Screen.INCLUIR_CLIENTE);
	}
	@Override
	public String prepareUpdate(Cliente cliente) {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_CLIENTE);
	}
	@Override
	public String prepareDetail(Cliente cliente) {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_CLIENTE);
	}
	@Override
	public Cliente findById() {
		Integer id = null;
		return this.clienteDao.findById(id);
	}
	@Override
	public List<Cliente> findByFields(ClienteVO clienteVO) {
		return this.clienteDao.findByFields(clienteVO);
	}
	@Override
	public boolean isRecordExists(Cliente entity) {
		return this.clienteDao.isRecordExists(entity);
	}
	@Override
	public boolean isCpfExists(Cliente entity) {
		return this.clienteDao.isRecordExists(entity);
	}
	public IClienteDAO getClienteDao() {
		return clienteDao;
	}
	public void setClienteDao(IClienteDAO clienteDao) {
		this.clienteDao = clienteDao;
	}
}