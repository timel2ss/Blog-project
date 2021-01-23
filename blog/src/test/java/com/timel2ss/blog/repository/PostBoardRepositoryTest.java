package com.timel2ss.blog.repository;

import com.timel2ss.blog.domain.PostBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PostBoardRepositoryTest {

    @Autowired PostBoardRepository postBoardRepository;

    @Test
    public void save() {
        // given
        PostBoard postBoard = PostBoard.createBoard("name", "description");

        // when
        postBoardRepository.save(postBoard);

        // then
        PostBoard result = postBoardRepository.findOne(postBoard.getId());
        assertThat("name").isEqualTo(result.getName());
        assertThat("description").isEqualTo(result.getDescription());
        assertThat(postBoard).isEqualTo(result);
    }

    @Test
    @Commit
    public void remove() {
        // given
        PostBoard postBoard = PostBoard.createBoard("name", "description");
        postBoardRepository.save(postBoard);

        // when
        postBoardRepository.remove(postBoard);

        // then
        PostBoard result = postBoardRepository.findOne(postBoard.getId());
        assertThat(result).isNull();
    }
}