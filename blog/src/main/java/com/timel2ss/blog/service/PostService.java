package com.timel2ss.blog.service;

import com.timel2ss.blog.domain.Post;
import com.timel2ss.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post posts(Post post) {
        return postRepository.save(post);
    }
}
