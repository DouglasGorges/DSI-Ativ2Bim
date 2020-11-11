package br.edu.up.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericDAO<T> implements DAO<T> {
    private Class<T> persistedClass;
    protected EntityManager entityManager;

    protected GenericDAO() {
        Type t = this.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType)t;
        this.persistedClass = (Class)pt.getActualTypeArguments()[0];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ativ2bim");
        this.entityManager = emf.createEntityManager();
    }

    public T salvar(T entidade) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        this.entityManager.persist(entidade);
        this.entityManager.flush();
        t.commit();
        return entidade;
    }

    public void apagar(Integer id) {
        T entidade = this.buscarPorId(id);
        EntityTransaction tx = this.entityManager.getTransaction();
        tx.begin();
        T mergedEntity = this.entityManager.merge(entidade);
        this.entityManager.remove(mergedEntity);
        this.entityManager.flush();
        tx.commit();
    }

    public List<T> listar() {
        String query = "select l from " + this.persistedClass.getName() + " l";
        return this.entityManager.createQuery(query, this.persistedClass).getResultList();
    }

    public T atualizar(T entidade) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        this.entityManager.merge(entidade);
        this.entityManager.flush();
        t.commit();
        return entidade;
    }

    public T buscarPorId(Integer id) {
        return this.entityManager.find(this.persistedClass, id);
    }
}
