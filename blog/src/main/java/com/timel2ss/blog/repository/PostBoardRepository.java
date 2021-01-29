package com.timel2ss.blog.repository;

import com.timel2ss.blog.domain.PostBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostBoardRepository {

    private final EntityManager em;

    public PostBoard save(PostBoard postBoard) {
        em.persist(postBoard);
        return postBoard;
    }

    public PostBoard findOne(long id) {
        return em.find(PostBoard.class, id);
    }

    public List<PostBoard> findAll() {
        return em.createQuery("select b from PostBoard b", PostBoard.class)
                .getResultList();
    }

    public void remove(PostBoard postBoard) {
        em.remove(postBoard);
    }

    public long countPostsById(long id) {
        return em.createQuery("select count(p) from Post p where p.postBoard.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public long countAll() {
        return em.createQuery("select count(p) from Post p", Long.class)
                .getSingleResult();
    }
}
