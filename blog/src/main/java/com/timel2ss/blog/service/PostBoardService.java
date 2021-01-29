package com.timel2ss.blog.service;

import com.timel2ss.blog.domain.PostBoard;
import com.timel2ss.blog.dto.PostBoardDto;
import com.timel2ss.blog.repository.PostBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostBoardService {

    private final PostBoardRepository postBoardRepository;

    @Transactional
    public long create(PostBoardDto.Create create) {
        PostBoard postBoard = PostBoard.createBoard(create.getName(), create.getDescription());
        postBoardRepository.save(postBoard);
        return postBoard.getId();
    }

    public PostBoardDto.Response getPostBoard(long id) {
        PostBoard postBoard = postBoardRepository.findOne(id);
        long count = postBoardRepository.countPostsById(id);
        return new PostBoardDto.Response(postBoard.getId(), postBoard.getName(), postBoard.getDescription(), count);
    }

    public List<PostBoardDto.Response> getCategories() {
        List<PostBoard> boards = postBoardRepository.findAll();
        List<PostBoardDto.Response> result = new ArrayList<>();

        for (PostBoard board : boards) {
            long count = postBoardRepository.countPostsById(board.getId());
            PostBoardDto.Response response = new PostBoardDto.Response(board.getId(), board.getName(), board.getDescription(), count);
            result.add(response);
        }
        return result;
    }

    public long countAll() {
        return postBoardRepository.countAll();
    }
}
