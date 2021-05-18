package br.com.unip.tcc.comparator;

import java.util.Comparator;

import br.com.unip.tcc.model.CotacaoItens;
public class CotacaoItensComparator implements Comparator<CotacaoItens> {
	@Override
	public int compare(CotacaoItens o1, CotacaoItens o2) {
		CotacaoItens c1 = (CotacaoItens) o1;
		CotacaoItens c2 = (CotacaoItens) o2;
        return c1.getFornecedor().getId() < c2.getFornecedor().getId() ? -1 :
        	   (c1.getFornecedor().getId() > c2.getFornecedor().getId() ? +1 : 0);
	}
}
