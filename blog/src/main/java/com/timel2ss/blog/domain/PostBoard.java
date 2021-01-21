package com.timel2ss.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class PostBoard {

    @Id @GeneratedValue
    @Column(name = "post_board_id")
    private long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "postBoard", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
