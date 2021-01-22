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
public class PostBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_board_id")
    private long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "postBoard", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    // 생성 메서드
    public static PostBoard createBoard(String name, String description) {
        PostBoard postBoard = new PostBoard();
        postBoard.name = name;
        postBoard.description = description;
        return postBoard;
    }

    // 수정
    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
