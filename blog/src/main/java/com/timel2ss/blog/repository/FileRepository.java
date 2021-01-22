package com.timel2ss.blog.repository;

import com.timel2ss.blog.domain.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public File save(File file) {
        em.persist(file);
        return file;
    }

    public List<File> findByPost(long postId) {
        return em.createQuery("select f from File f where f.post.id = :post_id", File.class)
                .setParameter("post_id", postId)
                .getResultList();
    }
}
