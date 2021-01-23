package com.timel2ss.blog.repository;

import com.timel2ss.blog.domain.NoticeStatus;
import com.timel2ss.blog.domain.Post;
import com.timel2ss.blog.domain.PostBoard;
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
class PostRepositoryTest {

    @Autowired PostRepository postRepository;
    @Autowired PostBoardRepository postBoardRepository;

    @Test
    public void save() {
        // given
        Post post = Post.createPost(null, null, "title", "description", "content", NoticeStatus.POST);
        postRepository.save(post);

        // when
        Post result = postRepository.findOne(post.getId());

        // then
        assertThat("title").isEqualTo(result.getTitle());
        assertThat("description").isEqualTo(result.getDescription());
        assertThat("content").isEqualTo(result.getContent());
        assertThat(NoticeStatus.POST).isEqualTo(result.getNoticeStatus());
        assertThat(post.getCreateDate()).isEqualTo(post.getModifyDate());
        assertThat(post).isEqualTo(result);
    }

    @Test
    public void remove() {
        // given
        Post post = Post.createPost(null, null, "title", "description", "content", NoticeStatus.POST);
        postRepository.save(post);

        // when
        postRepository.remove(post);

        // then
        Post result = postRepository.findOne(post.getId());
        assertThat(result).isNull();
    }

    @Test
    public void findByPostBoard() {
        // given
        PostBoard postBoard = PostBoard.createBoard("postBoard", "description");
        postBoardRepository.save(postBoard);

        Post post1 = Post.createPost(null, postBoard, "title", "description", "content", NoticeStatus.POST);
        postRepository.save(post1);
        Post post2 = Post.createPost(null, postBoard, "title2", "description", "content", NoticeStatus.POST);
        postRepository.save(post2);

        List<Post> list = new ArrayList<>();
        list.add(post1);
        list.add(post2);

        // when
        List<Post> result = postRepository.findByPostBoard(postBoard.getId(), 0);

        // then
        for (int i = 0; i < result.size(); i++) {
             assertThat(list.get(i).toString()).isEqualTo(result.get(i).toString());
        }
    }
}