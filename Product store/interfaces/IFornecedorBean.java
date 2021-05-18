package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Fornecedor;
import br.com.unip.tcc.service.FornecedorService;
import br.com.unip.tcc.vo.FornecedorVO;

public interface IFornecedorBean {
	List<Fornecedor> getListAll();
	void setListAll(List<Fornecedor> listAll);
	Fornecedor getFornecedor();
	void setFornecedor(Fornecedor fornecedor);
	boolean isDetail();
	void setDetail(boolean detail);
	FornecedorVO getFornecedorVo();
	void setFornecedorVo(FornecedorVO fornecedorVo);
	String newRecord();
	void save() throws FieldsUnfilledException;
	void remove() throws CannotDeleteException;
	String prepareUpdate(Fornecedor fornecedor);
	String prepareDetail(Fornecedor fornecedor);
	String cancel();
	void findByFields();
	FornecedorService getFornecedorService();
	void setFornecedorService(IFornecedorService fornecedorService);
}
