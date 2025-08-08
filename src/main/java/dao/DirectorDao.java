package dao;

import entity.Director;

import java.util.List;

public class DirectorDao extends EntityDao<Director> {
    @Override
    public List<Director> findAll() {
        return em.createQuery("from Director", Director.class)
                .getResultList();
    }

    @Override
    public Director findById(Integer id) {
        return em.find(Director.class, id);
    }

    public Director findByName(String name) {
        return em.createQuery("from Director where name = :name", Director.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
