package com.timel2ss.blog.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class PostBoardTest {

    @Test
    public void createBoard() {
        // given
        String name = "Spring lecture";
        String description = "make something with spring";

        // when
        PostBoard postBoard = PostBoard.createBoard(name, description);

        // then
        assertThat(name).isEqualTo(postBoard.getName());
        assertThat(description).isEqualTo(postBoard.getDescription());
    }

    @Test
    public void update() {
        // given
        String name = "Spring lecture";
        String description = "make something with spring";
        PostBoard postBoard = PostBoard.createBoard(name, description);

        // when
        String name2 = "Spring boot lecture";
        String description2 = "make something with spring boot";
        postBoard.update(name2, description2);

        // then
        assertThat(name2).isEqualTo(postBoard.getName());
        assertThat(description2).isEqualTo(postBoard.getDescription());
    }
}