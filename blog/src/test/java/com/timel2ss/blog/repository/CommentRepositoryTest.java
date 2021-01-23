package com.timel2ss.blog.repository;

import com.timel2ss.blog.domain.Comment;
import com.timel2ss.blog.domain.NoticeStatus;
import com.timel2ss.blog.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Autowired PostRepository postRepository;

    @Test
    public void findAll() {
        // given
        Post post = Post.createPost(null, null, "title", "description", "content", NoticeStatus.POST);
        postRepository.save(post);

        Comment comment1 = Comment.createComment(post, "nick1", "pass", "127.0.0.1", "content1");
        Comment comment2 = Comment.createComment(post, "nick2", "pass", "127.0.0.1", "content2");
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        // when
        List<Comment> result = commentRepository.findAll(post.getId());

        // then
        for (int i = 0; i < result.size(); i++) {
             assertThat(comments.get(i)).isEqualTo(result.get(i));
             assertThat(comments.get(i).getNickname()).isEqualTo(result.get(i).getNickname());
             assertThat(comments.get(i).getPassword()).isEqualTo(result.get(i).getPassword());
             assertThat(comments.get(i).getIP()).isEqualTo(result.get(i).getIP());
             assertThat(comments.get(i).getContent()).isEqualTo(result.get(i).getContent());
        }
    }
}