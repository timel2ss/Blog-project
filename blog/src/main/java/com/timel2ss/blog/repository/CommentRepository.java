package com.timel2ss.blog.repository;

import com.timel2ss.blog.domain.Comment;
import com.timel2ss.blog.domain.DeleteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    public List<Comment> findAll(long postId) {
        return em.createQuery("select c from Comment c where c.deleteStatus = :status and c.post.id = :id", Comment.class)
                .setParameter("status", DeleteStatus.POST)
                .setParameter("id", postId)
                .getResultList();
    }
}
