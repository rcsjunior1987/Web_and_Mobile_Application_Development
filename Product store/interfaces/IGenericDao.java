package br.com.unip.tcc.interfaces;

import java.util.List;

import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;

public interface IGenericDao<T, TVO> {
	boolean insert(T entity) throws FieldsUnfilledException;
	boolean update(T entity) throws FieldsUnfilledException;
	void remove(T entity) throws CannotDeleteException;
	T findById(Integer id);
	List<T> findByFields(TVO entityVo);
	List<?> findByFields(String jpql);
	boolean insertAnyRecord(List<T> listRecord);
	boolean updateAnyRecord(List<T> listRecord);
	boolean isRecordExists(T entity);
}
