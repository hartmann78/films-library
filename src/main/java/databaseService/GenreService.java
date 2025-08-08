package databaseService;

import dao.EntityDao;
import dao.impl.GenreDaoImpl;
import entity.Genre;

import java.util.List;
import java.util.Optional;

public class GenreService {
    private final EntityDao<Genre> genreDao = new GenreDaoImpl();

    public List<Genre> findAllGenres() {
        return genreDao.findAll();
    }

    public Optional<Genre> findGenreById(Integer id) {
        return genreDao.findById(id);
    }

    public Optional<Genre> findGenreByName(String name) {
        return genreDao.findByName(name);
    }

    public void addGenre(Genre genre) {
        genreDao.add(genre);
    }

    public void updateGenre(Genre genre) {
        genreDao.update(genre);
    }

    public void deleteGenre(Genre genre) {
        genreDao.delete(genre);
    }
}