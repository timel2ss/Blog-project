package com.timel2ss.blog.dto;

import com.timel2ss.blog.domain.File;
import com.timel2ss.blog.domain.NoticeStatus;
import com.timel2ss.blog.domain.PostBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class PostDto {

    @Getter
    @RequiredArgsConstructor
    public static class Create {
        private final String title;
        private final String description;
        private final String content;
        private final NoticeStatus noticeStatus;
        private final AdminDto adminDto;
        private PostBoard postBoard;
        private List<File> files = new ArrayList<>();
    }
}
