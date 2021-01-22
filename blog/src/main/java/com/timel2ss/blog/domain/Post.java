package com.timel2ss.blog.domain;

import com.timel2ss.blog.exception.WrongPasswordException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long id;

    private String title;
    private String description;
    private String content;

    @Enumerated(value = EnumType.STRING)
    private NoticeStatus noticeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_board_id")
    private PostBoard postBoard;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

    // 생성 메서드
    public static Post createPost(Admin admin, PostBoard postBoard, String title, String description, String content, NoticeStatus noticeStatus, List<File> files) {
        Post post = new Post();
        post.admin = admin;
        post.postBoard = postBoard;
        post.title = title;
        post.description = description;
        post.content = content;
        post.noticeStatus = noticeStatus;
        post.files.addAll(files);
        return post;
    }

    // 내용 수정
    public void modify(String password, String title, String description, String content, NoticeStatus noticeStatus, File... files) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.noticeStatus = noticeStatus;
        Arrays.stream(files).filter(Objects::nonNull).forEach(file -> this.files.add(file));
    }

    // 첨부 파일 삭제
    public void deleteFile(long fileId) {
        files.stream().filter(file -> file.getId() == fileId).forEach(File::delete);
    }

    // 댓글 삭제
    public void deleteComment(long commentId, String password) {
        comments.stream().filter(comment -> comment.getId() == commentId).forEach(comment -> comment.delete(password));
    }

    // 게시판 이동
    public void movePostBoard(PostBoard postBoard) {
        this.postBoard = postBoard;
        postBoard.getPosts().add(this);
    }
}
