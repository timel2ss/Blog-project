package com.timel2ss.blog.service;

import com.timel2ss.blog.domain.Comment;
import com.timel2ss.blog.domain.Post;
import com.timel2ss.blog.dto.CommentDto;
import com.timel2ss.blog.repository.CommentRepository;
import com.timel2ss.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public long createComment(CommentDto.Create create, String IP) {
        Post post = postRepository.findOne(create.getPostId());
        Comment comment = Comment.createComment(post, create.getNickname(), create.getPassword(), IP, create.getContent());
//        commentRepository.save(comment);
        post.addComment(comment);
        return comment.getId();
    }

    public List<CommentDto.Response> getComments(long id) {
        List<Comment> comments = commentRepository.findAll(id);
        List<CommentDto.Response> responses = new LinkedList<>();

        for (Comment comment : comments) {
            CommentDto.Response response = new CommentDto.Response(comment.getId(), comment.getNickname(), comment.getContent(), comment.getCreateDate());
            responses.add(response);
        }
        return responses;
    }

}
