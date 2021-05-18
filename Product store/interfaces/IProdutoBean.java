package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.service.ProdutoService;
import br.com.unip.tcc.vo.ProdutoVO;

public interface IProdutoBean {
	void setListAll(List<Produto> listAll);
	List<Produto> getListAll();
	Produto getProduto();
	void setProduto(Produto produto);
	boolean isDetail();
	void setDetail(boolean detail);
	void setProdutoVo(ProdutoVO produtoVo);
	ProdutoVO getProdutoVo();
	String newRecord();
	void save() throws FieldsUnfilledException;
	void remove() throws CannotDeleteException;
	String prepareUpdate(Produto produto);
	String prepareDetail(Produto produto);
	String cancel();
	void findByFields();
	ProdutoService getProdutoService();
	void setProdutoService(ProdutoService produtoService);
}
