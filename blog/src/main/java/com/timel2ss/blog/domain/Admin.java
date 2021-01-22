package com.timel2ss.blog.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private long id;

    private String name;
    private String password;

    @OneToMany(mappedBy = "admin")
    private List<Post> posts = new ArrayList<>();

    // 생성 메서드
    public static Admin createMember(String name, String password) {
        Admin admin = new Admin();
        admin.name = name;
        admin.password = password; // TODO decrypt
        return admin;
    }
}
