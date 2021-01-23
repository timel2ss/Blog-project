package com.timel2ss.blog.service;

import com.timel2ss.blog.domain.Admin;
import com.timel2ss.blog.domain.Post;
import com.timel2ss.blog.domain.PostBoard;
import com.timel2ss.blog.dto.PostDto;
import com.timel2ss.blog.repository.AdminRepository;
import com.timel2ss.blog.repository.PostBoardRepository;
import com.timel2ss.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final AdminRepository adminRepository;
    private final PostRepository postRepository;
    private final PostBoardRepository postBoardRepository;

    @Transactional
    public long posts(PostDto.Create create) {
        Admin admin = adminRepository.findOne(create.getId());
        PostBoard postBoard = postBoardRepository.findOne(create.getPostBoardId());
        Post post = Post.createPost(admin, postBoard, create.getTitle(), create.getDescription(), create.getContent(), create.getNoticeStatus());
        postRepository.save(post);
        return post.getId();
    }

    public PostDto.Response getPost(long id) {
        Post post = postRepository.findOne(id);
        return new PostDto.Response(post.getId(), post.getPostBoard().getId(), post.getTitle(), post.getDescription(), post.getContent(), post.getCreateDate());
    }

    public List<PostDto.Response> getPosts(long boardId, int start) {
        List<Post> list = postRepository.findByPostBoard(boardId, start);
        List<PostDto.Response> result = new LinkedList<>();

        for (Post post : list) {
            PostDto.Response response = new PostDto.Response(post.getId(), boardId, post.getTitle(), post.getDescription(), post.getContent(), post.getCreateDate());
            result.add(response);
        }
        return result;
    }
}
