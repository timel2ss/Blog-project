package com.timel2ss.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Admin {

    @Id @GeneratedValue
    @Column(name = "admin_id")
    private long id;

    private String name;
    private String password;

    @OneToMany(mappedBy = "admin")
    private List<Post> posts = new ArrayList<>();
}
