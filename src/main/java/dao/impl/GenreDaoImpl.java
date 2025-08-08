package dao.impl;

import dao.EntityDao;
import jakarta.persistence.*;
import entity.Genre;

import java.util.List;
import java.util.Optional;

public class GenreDaoImpl implements EntityDao<Genre> {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    EntityManager em = factory.createEntityManager();

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("from Genre", Genre.class);
        return query.getResultList();
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        try {
            return Optional.of(em.find(Genre.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Genre> findByName(String name) {
        try {
            TypedQuery<Genre> query = em.createQuery("from Genre where name = :name", Genre.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void add(Genre genre) {
        try {
            em.getTransaction().begin();
            em.persist(genre);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Genre genre) {
        try {
            em.getTransaction().begin();
            em.merge(genre);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Genre genre) {
        try {
            em.getTransaction().begin();
            em.remove(genre);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
