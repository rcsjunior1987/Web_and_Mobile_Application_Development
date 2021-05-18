package br.com.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IProdutoDao;
import br.com.unip.tcc.interfaces.IProdutoService;
import br.com.unip.tcc.model.Produto;
import br.com.unip.tcc.system.Screen;
import br.com.unip.tcc.util.FacesUtil;
import br.com.unip.tcc.vo.ProdutoVO;

@Service("produtoService")
public class ProdutoService implements IProdutoService {

	@Autowired
	private IProdutoDao produtoDao;
	
	@Override
	public String newRecord(ProdutoVO produtoVo) {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_PRODUTO);
	}

	@Override
	public boolean insert(Produto entity) throws FieldsUnfilledException {
		return this.produtoDao.insert(entity);
	}

	@Override
	public boolean update(Produto entity) throws FieldsUnfilledException {
		return this.produtoDao.update(entity);
	}

	@Override
	public void remove(Produto entity) throws CannotDeleteException {
		this.produtoDao.remove(entity);
		
	}

	@Override
	public String cancel(Produto entity) {
		return FacesUtil.facesRedirectUrl(Screen.INCLUIR_PRODUTO);
	}

	@Override
	public String prepareUpdate(Produto produto) {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_PRODUTO);
	}

	@Override
	public String prepareDetail(Produto produto) {
		return FacesUtil.facesRedirectUrl(Screen.LISTAR_PRODUTO);
	}

	@Override
	public Produto findById(Integer id) {
		return this.produtoDao.findById(id);
	}

	@Override
	public List<Produto> findByFields(ProdutoVO produtoVO) {
		return this.produtoDao.findByFields(produtoVO);
	}

	@Override
	public boolean isRecordExists(Produto entity) {
		return this.produtoDao.isRecordExists(entity);
	}

}
