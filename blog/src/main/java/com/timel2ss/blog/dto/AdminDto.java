package com.timel2ss.blog.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class AdminDto {

    @Getter
    @RequiredArgsConstructor
    public static class Create {
        private final String name;
        private final String password;
    }

    //public static class
}
