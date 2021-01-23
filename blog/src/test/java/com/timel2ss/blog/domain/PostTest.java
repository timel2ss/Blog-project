package com.timel2ss.blog.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class PostTest {

    @Test
    public void createPost() {
        // given
        String title = "111";
        String description = "222";
        String content = "333";
        NoticeStatus noticeStatus = NoticeStatus.NOTICE;

        // when
        Post post = Post.createPost(null, null, title, description, content, noticeStatus);

        // then
        assertThat(title).isEqualTo(post.getTitle());
        assertThat(description).isEqualTo(post.getDescription());
        assertThat(content).isEqualTo(post.getContent());
        assertThat(noticeStatus).isEqualTo(post.getNoticeStatus());
    }

    @Test
    public void modify() {
        // given
        Admin admin = Admin.createMember("kim", "paSSword");

        String title = "111";
        String description = "222";
        String content = "333";
        NoticeStatus noticeStatus = NoticeStatus.NOTICE;
        Post post = Post.createPost(admin, null, title, description, content, noticeStatus);

        // when
        String title2 = "1111";
        String description2 = "2222";
        String content2 = "3333";
        NoticeStatus noticeStatus2 = NoticeStatus.POST;
        post.modify(title2, description2, content2, noticeStatus2);

        // then
        assertThat(title2).isEqualTo(post.getTitle());
        assertThat(description2).isEqualTo(post.getDescription());
        assertThat(content2).isEqualTo(post.getContent());
        assertThat(noticeStatus2).isEqualTo(post.getNoticeStatus());
    }

    @Test
    public void addComment() {
        // given
        String title = "111";
        String description = "222";
        String content = "333";
        NoticeStatus noticeStatus = NoticeStatus.NOTICE;
        Post post = Post.createPost(null, null, title, description, content, noticeStatus);
        Comment comment = Comment.createComment(post, "nick", "pass", "127.0.0.1", "content");

        // when
        post.addComment(comment);

        // then
        Comment comment1 = post.getComments().get(0);
        assertThat(1).isEqualTo(post.getComments().size());
        assertThat("nick").isEqualTo(comment1.getNickname());
        assertThat("pass").isEqualTo(comment1.getPassword());
        assertThat("127.0.0.1").isEqualTo(comment1.getIP());
        assertThat("content").isEqualTo(comment1.getContent());
    }

    @Test
    public void deleteComment() {
        // given
        String title = "111";
        String description = "222";
        String content = "333";
        NoticeStatus noticeStatus = NoticeStatus.NOTICE;
        Post post = Post.createPost(null, null, title, description, content, noticeStatus);
        Comment comment = Comment.createComment(post, "nick", "pass", "127.0.0.1", "content");
        post.addComment(comment);

        // when
        post.deleteComment(comment.getId(), "pass");


        // then
        assertThat(DeleteStatus.DELETE).isEqualTo(comment.getDeleteStatus());
        assertThat(DeleteStatus.DELETE).isEqualTo(post.getComments().get(0).getDeleteStatus());
    }

    @Test
    void movePostBoard() {
    }
}