package com.timel2ss.blog.domain;

import com.timel2ss.blog.exception.WrongPasswordException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;

    private String nickname;
    private String password;
    private String IP;
    private String content;

    @Enumerated(value = EnumType.STRING)
    private DeleteStatus deleteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 생성 메서드
    public static Comment createComment(Post post, String nickname, String password, String IP, String content) {
        Comment comment = new Comment();
        comment.nickname = nickname;
        comment.password = password; // TODO decrypt
        comment.IP = IP;
        comment.content = content;

        comment.deleteStatus = DeleteStatus.POST;
        comment.post = post;
        return comment;
    }

    // 내용 수정
    public void update(String password, String content) {
        validatePassword(password);
        this.content = content;
    }

    // 삭제
    public void delete(String password) {
        validatePassword(password);
        this.deleteStatus = DeleteStatus.DELETE;
    }

    // 예외 검증
    private void validatePassword(String password) {
        if(!this.password.equals(password)) {
            throw new WrongPasswordException("잘못된 패스워드입니다.");
        }
    }
}
