package br.com.unip.tcc.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.exception.ConstraintViolationException;
import br.com.unip.tcc.exceptions.CannotDeleteException;
import br.com.unip.tcc.exceptions.FieldsUnfilledException;
import br.com.unip.tcc.interfaces.IGenericDao;

public abstract class GenericDao<T, TVO> implements IGenericDao<T, TVO> {

	//@PersistenceContext
	private EntityManager entityManager;

	private Class<T> persistentClass;

	public GenericDao() {}
	
	@SuppressWarnings("unchecked")
	public GenericDao(EntityManager entityManager) {
		setEntityManager(entityManager);
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private Query createQuery(String jpql) {
		return this.getEntityManager().createQuery(jpql);
	}

	@Override
	public boolean insert(T entity) throws FieldsUnfilledException {
		try {
			this.getEntityManager().persist(entity);
			this.getEntityManager().flush();
			return true;
		} catch (Exception e) {
			throw new FieldsUnfilledException();
		}
	}

	@Override
	public boolean update(T entity) throws FieldsUnfilledException {
		this.getEntityManager().merge(entity);
		return true;
	}
	
	@Override
	public void remove(T entity) throws CannotDeleteException {
		this.getEntityManager().remove(this.getEntityManager().contains(entity) ? entity : this.getEntityManager().merge(entity));
	}

	@Override
	public T findById(Integer id) {
		return this.getEntityManager().find(this.getPersistentClass(), id);
	}

	@Override
	public List<?> findByFields(String jpql) {
		return this.createQuery(jpql).getResultList();
	}

	@Override
	public boolean insertAnyRecord(List<T> listRecord) {
		try {
			for (T t : listRecord) {
				try {
					this.insert(t);
				} catch (FieldsUnfilledException e) {
					e.printStackTrace();
				}
			}
			
			return true;
		} catch (ConstraintViolationException e) {
			return false;
		}
	}

	@Override
	public boolean updateAnyRecord(List<T> listRecord) {
		try {
			for (T t : listRecord) {
				try {
					this.update(t);
				} catch (FieldsUnfilledException e) {
					e.printStackTrace();
				}
			}
			return true;
		} catch (ConstraintViolationException e) {
			return false;
		}
	}

	@Override
	public boolean isRecordExists(T entity) {
		return false;
	}

	public boolean isExistsRecord(String hql) {
		List<?> findByFields = this.findByFields(hql);
		return findByFields.size() == 0 ? false : true;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
}
