package dao;

import entity.Genre;

import java.util.List;

public class GenreDao extends EntityDao<Genre> {
    @Override
    public List<Genre> findAll() {
        return em.createQuery("from Genre", Genre.class)
                .getResultList();
    }

    @Override
    public Genre findById(Integer id) {
        return em.find(Genre.class, id);
    }

    public Genre findByName(String name) {
        return em.createQuery("from Genre where name = :name", Genre.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
