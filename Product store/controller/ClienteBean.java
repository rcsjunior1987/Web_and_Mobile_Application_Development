package br.com.unip.tcc.controller;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IClienteBean;
import br.com.unip.tcc.interfaces.IClienteService;
import br.com.unip.tcc.model.Cliente;
import br.com.unip.tcc.service.ClienteService;
import br.com.unip.tcc.system.Messages;
import br.com.unip.tcc.util.FacesMessageUtil;
import br.com.unip.tcc.vo.ClienteVO;

@Controller("clienteBean")
public class ClienteBean implements IClienteBean, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public IClienteService clienteService;

	private Cliente cliente;
	private ClienteVO clienteVo;
	private List<Cliente> listAll = null;
	private static boolean detail = false;
	
	public ClienteBean() {}
	
	@Override
	public List<Cliente> getListAll() {
		if(this.listAll == null) {
			if(this.clienteVo == null) {
				this.clienteVo = new ClienteVO();
			}
			this.listAll = clienteService.findByFields(clienteVo);
		}
		return this.listAll;
	}
	@Override
	public void setListAll(List<Cliente> listAll) {
		this.listAll = listAll;
	}
	@Override
	public Cliente getCliente() {
		if (this.cliente == null) {
			this.cliente = new Cliente();
			this.cliente.setSexo("M");
		}
		return this.cliente;
	}
	@Override
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public boolean isDetail() {
		return detail;
	}
	@Override
	public void setDetail(boolean detail) {
		ClienteBean.detail = detail;
	}
	@Override
	public ClienteVO getClienteVo() {
		if (this.clienteVo == null) {
			this.clienteVo = new ClienteVO();
			this.clienteVo.setSexo("D");
		}
		return this.clienteVo;
	}
	@Override
	public void setClienteVo(ClienteVO clienteVo) {
		this.clienteVo = clienteVo;
	}
	@Override
	public String newRecord() {
		this.cliente = new Cliente();
		this.cliente.setSexo("M");
		return this.clienteService.newRecord(this.clienteVo);
	}
	@Override
	public void save() throws FieldsUnfilledException {
		try {
				if (this.clienteService.isRecordExists(this.cliente))
					FacesMessageUtil.showMessageError(Messages.M3);
				else {
					if (this.cliente.getId() == null || this.cliente.getId() == 0) {
						if (this.clienteService.insert(this.cliente)) {
							this.listAll = clienteService.findByFields(clienteVo);
							FacesMessageUtil.showMessageInfo(Messages.M5);
						}
					} else {
						if (this.clienteService.update(this.cliente))
							FacesMessageUtil.showMessageInfo(Messages.M6);
					}
			}
		} catch (FieldsUnfilledException e) {
			FacesMessageUtil.showMessageError(Messages.M2);
		}
	}
	@Override
	public void remove() throws CannotDeleteException {
		try {
				this.clienteService.remove(this.cliente);
				this.listAll = this.clienteService.findByFields(this.clienteVo);
		} catch (CannotDeleteException e) {
				FacesMessageUtil.showMessageInfo(Messages.M11);
		}
	}
	@Override
	public String prepareUpdate(Cliente cliente) {
		this.cliente = cliente;
		return this.clienteService.prepareUpdate(cliente);
	}
	@Override
	public String prepareDetail(Cliente cliente) {
		ClienteBean.detail = true;
		this.cliente = cliente;
		return this.clienteService.prepareDetail(cliente);
	}
	@Override
	public String cancel() {
		ClienteBean.detail = false;
		return this.clienteService.cancel(this.cliente);
	}
	@Override
	public void findByFields() {
		this.listAll = this.clienteService.findByFields(this.clienteVo);
	}
	@Override
	public ClienteService getClienteService() {
		return null;
	}
	@Override
	public void setClienteService(ClienteService clienteService) {
	}
}
