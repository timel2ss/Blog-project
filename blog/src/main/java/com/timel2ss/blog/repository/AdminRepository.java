package com.timel2ss.blog.repository;

import com.timel2ss.blog.domain.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

    private final EntityManager em;

    public Admin save(Admin admin) {
        em.persist(admin);
        return admin;
    }

    public Admin findOne(long id) {
        return em.find(Admin.class, id);
    }
}
