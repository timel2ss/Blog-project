package com.timel2ss.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private long id;

    private String nickname;
    private String password;
    private String IP;
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @Enumerated(value = EnumType.STRING)
    private DeleteStatus deleteFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
