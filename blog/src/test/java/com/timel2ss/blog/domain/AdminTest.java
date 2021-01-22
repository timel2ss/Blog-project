package com.timel2ss.blog.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class AdminTest {

    @Test
    public void createMember() {
        // given
        String name = "kim";
        String password = "paSSword";

        // when
        Admin admin = Admin.createMember(name, password);

        // then
        assertThat(name).isEqualTo(admin.getName());
        assertThat(password).isEqualTo(admin.getPassword());
    }
}