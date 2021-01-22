package com.timel2ss.blog.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private long id;

    private String contentType;
    private String fileName;
    private String saveFileName;
    private String location;

    private LocalDateTime uploadDate;

    @Enumerated(value = EnumType.STRING)
    private DeleteStatus deleteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 생성 메서드
    public static File uploadFile(Post post, String contentType, String fileName, String saveFileName, String location) {
        File file = new File();
        file.contentType = contentType;
        file.fileName = fileName;
        file.saveFileName = saveFileName;
        file.location = location;
        file.uploadDate = LocalDateTime.now();
        file.deleteStatus = DeleteStatus.POST;
        file.post = post;
        return file;
    }

    // 파일 삭제
    public void delete() {
        this.deleteStatus = DeleteStatus.DELETE;
    }
}
