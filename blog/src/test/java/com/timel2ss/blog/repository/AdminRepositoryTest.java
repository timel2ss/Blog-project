package com.timel2ss.blog.repository;

import com.timel2ss.blog.domain.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminRepositoryTest {

    @Autowired AdminRepository adminRepository;

    @Test
    public void save() {
        // given
        Admin admin = Admin.createMember("kim", "paSSword");

        // when
        adminRepository.save(admin);

        // then
        Admin result = adminRepository.findOne(admin.getId());
        assertThat("kim").isEqualTo(result.getName());
        assertThat("paSSword").isEqualTo(result.getPassword());
        assertThat(admin).isEqualTo(result);
    }
}