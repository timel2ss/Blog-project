package com.timel2ss.blog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class File {

    @Id @GeneratedValue
    @Column(name = "file_id")
    private long fileId;

    private String contentType;
    private String fileName;
    private String saveFileName;
    private String location;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @Enumerated(value = EnumType.STRING)
    private DeleteStatus deleteFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
