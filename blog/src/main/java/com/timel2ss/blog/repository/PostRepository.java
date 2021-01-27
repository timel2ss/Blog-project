package com.timel2ss.blog.repository;

import com.timel2ss.blog.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    public Post findOne(long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findByPostBoard(long boardId, int start) {
        return em.createQuery("select p from Post p, PostBoard b where p.postBoard.id = b.id and b.id = :board_id", Post.class)
                .setParameter("board_id", boardId)
                .setFirstResult(start)
                .setMaxResults(5)
                .getResultList();
    }

    public List<Post> findAllOrderByDesc(int start) {
        return em.createQuery("select p from Post p order by p.id desc", Post.class)
                .setFirstResult(start)
                .setMaxResults(5)
                .getResultList();
    }

    public void remove(Post post) {
        em.remove(post);
    }
}
