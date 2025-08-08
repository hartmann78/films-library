package dao.impl;

import dao.EntityDao;
import entity.Actor;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class ActorDaoImpl implements EntityDao<Actor> {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    EntityManager em = factory.createEntityManager();

    @Override
    public List<Actor> findAll() {
        TypedQuery<Actor> query = em.createQuery("from Actor", Actor.class);
        return query.getResultList();
    }

    @Override
    public Optional<Actor> findById(Integer id) {
        try {
            return Optional.of(em.find(Actor.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Actor> findByName(String name) {
        try {
            TypedQuery<Actor> query = em.createQuery("from Actor where name = :name", Actor.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void add(Actor actor) {
        try {
            em.getTransaction().begin();
            em.persist(actor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Actor actor) {
        try {
            em.getTransaction().begin();
            em.merge(actor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Actor actor) {
        try {
            em.getTransaction().begin();
            em.remove(actor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
