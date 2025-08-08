package dao;

import dto.MovieByYear;
import entity.Film;

import java.util.List;

public class FilmDao extends EntityDao<Film> {
    @Override
    public List<Film> findAll() {
        return em.createQuery("from Film", Film.class).getResultList();
    }

    @Override
    public Film findById(Integer id) {
        return em.find(Film.class, id);
    }

    public List<Film> findByName(String name) {
        return em.createQuery("from Film where name = :name", Film.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Film> findFilmsByYear(MovieByYear movieByYear) {
        return em.createNamedQuery("Film.getFilteredByYear", Film.class)
                .setParameter("start", movieByYear.getStartYear())
                .setParameter("end", movieByYear.getEndYear())
                .getResultList();
    }

    public List<Film> findFilmsByDirector(String director) {
        return em.createQuery("from Film f, IN (f.directors) AS d where d.name = :director", Film.class)
                .setParameter("director", director)
                .getResultList();
    }

    public List<Film> findFilmsByActor(String actor) {
        return em.createQuery("from Film f, IN (f.actors) AS a where a.name = :actor", Film.class)
                .setParameter("actor", actor)
                .getResultList();
    }

    public List<Film> findTop10Films() {
        return em.createQuery("from Film order by rating desc", Film.class)
                .setMaxResults(10)
                .getResultList();
    }
}