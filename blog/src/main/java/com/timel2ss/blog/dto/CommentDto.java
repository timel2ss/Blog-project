package com.timel2ss.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @NoArgsConstructor
    public static class Create {
        private long id;
        private long postId;
        private String nickname;
        private String password;
        private String content;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final long id;
        private final String nickname;
        private final String content;
        private final LocalDateTime createTime;
    }
}
