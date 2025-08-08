package dao.impl;

import dao.EntityDao;
import entity.Director;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class DirectorDaoImpl implements EntityDao<Director> {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    EntityManager em = factory.createEntityManager();

    @Override
    public List<Director> findAll() {
        TypedQuery<Director> query = em.createQuery("from Director", Director.class);
        return query.getResultList();
    }

    @Override
    public Optional<Director> findById(Integer id) {
        try {
            return Optional.of(em.find(Director.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Director> findByName(String name) {
        try {
            TypedQuery<Director> query = em.createQuery("from Genre where name = :name", Director.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void add(Director director) {
        try {
            em.getTransaction().begin();
            em.persist(director);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Director director) {
        try {
            em.getTransaction().begin();
            em.merge(director);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Director director) {
        try {
            em.getTransaction().begin();
            em.remove(director);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
