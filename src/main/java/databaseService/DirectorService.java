package databaseService;

import dao.EntityDao;
import dao.impl.DirectorDaoImpl;
import entity.Director;

import java.util.List;
import java.util.Optional;

public class DirectorService {
    private final EntityDao<Director> directorDao = new DirectorDaoImpl();

    public List<Director> findAllDirectors() {
        return directorDao.findAll();
    }

    public Optional<Director> findDirectorById(Integer id) {
        return directorDao.findById(id);
    }

    public Optional<Director> findDirectorByName(String name) {
        return directorDao.findByName(name);
    }

    public void addDirector(Director director) {
        directorDao.add(director);
    }

    public void updateDirector(Director director) {
        directorDao.update(director);
    }

    public void deleteDirector(Director director) {
        directorDao.delete(director);
    }
}