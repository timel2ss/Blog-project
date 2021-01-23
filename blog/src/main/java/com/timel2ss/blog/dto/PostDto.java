package com.timel2ss.blog.dto;

import com.timel2ss.blog.domain.NoticeStatus;
import com.timel2ss.blog.domain.PostBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class PostDto {

    @Getter
    @NoArgsConstructor
    public static class Create {
        private long id;
        private long adminId;
        private long postBoardId;
        private String title;
        private String description;
        private String content;
        private NoticeStatus noticeStatus;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final long id;
        private final long postBoardId;
        private final String title;
        private final String description;
        private final String content;
        private final LocalDateTime createTime;
    }
}
