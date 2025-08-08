package dao;

import entity.Film;

import java.util.List;

public interface FilmDao extends EntityDao<Film> {
    List<Film> findFilmsByYear(Integer year);

    List<Film> findFilmsByDirector(String director);

    List<Film> findFilmsByActor(String actor);

    List<Film> findTop10Films();
}
