package br.com.tarssito.services;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;

import br.com.tarssito.util.Model;

public abstract class AbstractService<T extends Model<ID>, ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Class<T> type;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractService() {
		
		Class<?> clazz = getClass();
        do {
            if (clazz.getSuperclass().equals(AbstractService.class)) {
                break;
            }
        } while ((clazz = clazz.getSuperclass()) != null);

        this.type = (Class<T>) ((ParameterizedType) clazz
                .getGenericSuperclass()).getActualTypeArguments()[0];
        	
	}
	
	public T findById(Long id) {
        return this.entityManager.find(type, id);
    }

    public List<T> findAll() {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> rootEntry = cq.from(type);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Transactional
    public void remove(T object) {
        this.entityManager.remove(this.entityManager.getReference(this.type, object.getId()));
    }

    @Transactional
    public void create(T object) {
        this.entityManager.persist(object);
    }

    @Transactional
    public void update(T object) {
        this.entityManager.merge(object);
    }

    @Transactional
    public void save(T object) {
        if (object.getId() == null) {
            this.entityManager.persist(object);
        } else {
            this.entityManager.merge(object);
        }
    }

    protected Session createSession() {
        return (Session) this.entityManager.getDelegate();
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

	
}
