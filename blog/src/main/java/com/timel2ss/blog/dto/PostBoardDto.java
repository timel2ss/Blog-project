package com.timel2ss.blog.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class PostBoardDto {

    @Getter
    @RequiredArgsConstructor
    public static class Create {
        private final long id;
        private final String name;
        private final String description;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final long id;
        private final String name;
        private final String description;
        private final long count;
    }
}
