package databaseService;

import dao.EntityDao;
import dao.impl.ActorDaoImpl;
import entity.Actor;

import java.util.List;
import java.util.Optional;

public class ActorService {
    private final EntityDao<Actor> actorDao = new ActorDaoImpl();

    public List<Actor> findAllActors() {
        return actorDao.findAll();
    }

    public Optional<Actor> findActorById(Integer id) {
        return actorDao.findById(id);
    }

    public Optional<Actor> findActorByName(String name) {
        return actorDao.findByName(name);
    }

    public void addActor(Actor actor) {
        actorDao.add(actor);
    }

    public void updateActor(Actor actor) {
        actorDao.update(actor);
    }

    public void deleteActor(Actor actor) {
        actorDao.delete(actor);
    }
}