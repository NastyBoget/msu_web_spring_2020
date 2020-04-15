package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
public class GenericDAO<T, ID extends Serializable> {
    private Class<T> persistentClass;
    @Autowired
    SessionFactory sessionFactory;

    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T getById(ID id) {
        return  getCurrentSession().get(persistentClass, id);
    }

    public List<T> getAll() {
        CriteriaQuery<T> query =  getCurrentSession().getCriteriaBuilder().createQuery(persistentClass);
        query.from(persistentClass);
        return  getCurrentSession().createQuery(query).getResultList();
    }

    public void delete(T t) {
        getCurrentSession().delete(t);
    }

    public void save(T t) {
        getCurrentSession().save(t);
    }

    public void update(T t) {
        getCurrentSession().update(t);
    }
}