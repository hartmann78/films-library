package dao.impl;

import dao.FilmDao;
import jakarta.persistence.*;
import entity.Film;

import java.util.List;
import java.util.Optional;

public class FilmDaoImpl implements FilmDao {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    EntityManager em = factory.createEntityManager();

    @Override
    public List<Film> findAll() {
        TypedQuery<Film> query = em.createQuery("from Film", Film.class);
        return query.getResultList();
    }

    @Override
    public Optional<Film> findById(Integer id) {
        try {
            return Optional.of(em.find(Film.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Film> findByName(String name) {
        try {
            TypedQuery<Film> query = em.createQuery("from Film where name = :name", Film.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Film> findFilmsByYear(Integer year) {
        TypedQuery<Film> query = em.createQuery("from Film where year = :year", Film.class);
        query.setParameter("year", year);
        return query.getResultList();
    }

    @Override
    public List<Film> findFilmsByDirector(String director) {
        TypedQuery<Film> query = em.createQuery("from Film f, IN (f.directors) AS d where d.name = :director", Film.class);
        query.setParameter("director", director);
        return query.getResultList();
    }

    @Override
    public List<Film> findFilmsByActor(String actor) {
        TypedQuery<Film> query = em.createQuery("from Film f, IN (f.actors) AS a where a.name = :actor", Film.class);
        query.setParameter("actor", actor);
        return query.getResultList();
    }

    @Override
    public List<Film> findTop10Films() {
        TypedQuery<Film> query = em.createQuery("from Film order by rating desc limit 10", Film.class);
        return query.getResultList();
    }

    @Override
    public void add(Film film) {
        try {
            em.getTransaction().begin();
            em.persist(film);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Film film) {
        try {
            em.getTransaction().begin();
            em.merge(film);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Film film) {
        try {
            em.getTransaction().begin();
            em.remove(film);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}