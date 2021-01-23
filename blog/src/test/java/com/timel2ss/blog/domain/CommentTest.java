package com.timel2ss.blog.domain;

import com.timel2ss.blog.exception.WrongPasswordException;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Transactional
class CommentTest {

    @Test
    public void createComment() {
        // given
        String nickname = "kim_kim";
        String password = "paSSword";
        String IP = "127.0.0.1";
        String content = "content_content";

        // when
        Comment comment = Comment.createComment(null, nickname, password, IP, content);

        // then
        assertThat(nickname).isEqualTo(comment.getNickname());
        assertThat(password).isEqualTo(comment.getPassword());
        assertThat(IP).isEqualTo(comment.getIP());
        assertThat(content).isEqualTo(comment.getContent());
        assertThat(DeleteStatus.POST).isEqualTo(comment.getDeleteStatus());
        assertThat(comment.getCreateDate()).isEqualTo(comment.getModifyDate());
    }

    @Test
    public void update() {
        // given
        String nickname = "kim_kim";
        String password = "paSSword";
        String IP = "127.0.0.1";
        String content = "content_content";
        Comment comment = Comment.createComment(null, nickname, password, IP, content);

        // when
        String content2 = "content!!";
        comment.update(password, content2);

        // then
        assertThat(nickname).isEqualTo(comment.getNickname());
        assertThat(password).isEqualTo(comment.getPassword());
        assertThat(IP).isEqualTo(comment.getIP());
        assertThat(content2).isEqualTo(comment.getContent());
        assertThat(DeleteStatus.POST).isEqualTo(comment.getDeleteStatus());
    }

    @Test
    public void update_validatePassword() {
        // given
        String nickname = "kim_kim";
        String password = "paSSword";
        String IP = "127.0.0.1";
        String content = "content_content";
        Comment comment = Comment.createComment(null, nickname, password, IP, content);

        // when
        String wrongPW = "password";
        String content2 = "content!!";

        // then
        assertThrows(WrongPasswordException.class, () -> comment.update(wrongPW, content2));
    }

    @Test
    public void delete() {
        // given
        String nickname = "kim_kim";
        String password = "paSSword";
        String IP = "127.0.0.1";
        String content = "content_content";
        Comment comment = Comment.createComment(null, nickname, password, IP, content);

        // when
        comment.delete(password);

        // then
        assertThat(nickname).isEqualTo(comment.getNickname());
        assertThat(password).isEqualTo(comment.getPassword());
        assertThat(IP).isEqualTo(comment.getIP());
        assertThat(content).isEqualTo(comment.getContent());
        assertThat(DeleteStatus.DELETE).isEqualTo(comment.getDeleteStatus());
    }

    @Test
    public void delete_validatePassword() {
        // given
        String nickname = "kim_kim";
        String password = "paSSword";
        String IP = "127.0.0.1";
        String content = "content_content";
        Comment comment = Comment.createComment(null, nickname, password, IP, content);

        // when
        String wrongPW = "password";

        // then
        assertThrows(WrongPasswordException.class, () -> comment.delete(wrongPW));
    }
}