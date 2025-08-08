package dao;

import entity.Actor;

import java.util.List;

public class ActorDao extends EntityDao<Actor> {
    @Override
    public List<Actor> findAll() {
        return em.createQuery("from Actor", Actor.class)
                .getResultList();
    }

    @Override
    public Actor findById(Integer id) {
        return em.find(Actor.class, id);
    }

    public Actor findByName(String name) {
        return em.createQuery("from Actor where name = :name", Actor.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
