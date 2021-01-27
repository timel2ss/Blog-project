package com.timel2ss.blog.controller;

import com.timel2ss.blog.domain.Post;
import com.timel2ss.blog.dto.AdminDto;
import com.timel2ss.blog.dto.CommentDto;
import com.timel2ss.blog.dto.PostBoardDto;
import com.timel2ss.blog.dto.PostDto;
import com.timel2ss.blog.repository.PostRepository;
import com.timel2ss.blog.service.AdminService;
import com.timel2ss.blog.service.CommentService;
import com.timel2ss.blog.service.PostBoardService;
import com.timel2ss.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final AdminService adminService;
    private final PostService postService;
    private final CommentService commentService;
    private final PostBoardService postBoardService;

    @GetMapping("/")
    public String home(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
                       Model model) {
        List<PostBoardDto.Response> categories = postBoardService.getCategories();
        List<PostDto.Response> recentPosts = postService.getRecentPosts(start);

        model.addAttribute("categories", categories);
        model.addAttribute("posts", recentPosts);
        return "index";
    }

    @GetMapping("/board/{id}")
    public String postList(@PathVariable(name = "id") long id,
                           @RequestParam(name = "start") int start,
                           Model model) {
//        PostBoardDto.Response postBoard = postBoardService.getPostBoard(id);
//        List<PostBoardDto.Response> categories = postBoardService.getCategories();
//        List<PostDto.Response> posts = postService.getPosts(id, start);
//        model.addAttribute(postBoard);
//        model.addAttribute(categories);
//        model.addAttribute(posts);
        return "postBoard";
    }

    @GetMapping("/post/write")
    public String writeForm(Model model) {
//        PostDto.Create create = new PostDto.Create();
//        model.addAttribute("form", create);
        return "writeForm";
    }

    @PostMapping("/post/write")
    public String write(@RequestAttribute PostDto.Create create) {
        long id = postService.posts(create);
        return "redirect:post/" + id;
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable(name = "id") long id, Model model) {
//        AdminDto.Response admin = adminService.getInfo(id);
//        PostDto.Response post = postService.getPost(id);
//        List<CommentDto.Response> comments = commentService.getComments(id);
//        List<PostBoardDto.Response> categories = postBoardService.getCategories();
//        PostBoardDto.Response postBoard = postBoardService.getPostBoard(post.getPostBoardId());
//
//        model.addAttribute("admin", admin);
//        model.addAttribute("postBoard", postBoard);
//        model.addAttribute("post", post);
//        model.addAttribute("comments", comments);
//        model.addAttribute("categories", categories);
        return "postView";
    }

    @PostMapping("/post/{id}/comment")
    public String comment(@PathVariable(name = "id") long id,
                          @RequestAttribute CommentDto.Create create,
                          HttpServletRequest request) {
//        String IP = request.getHeader("X-Forwarded-For") != null ? request.getHeader("X-Forwarded-For") : request.getRemoteAddr();
//        commentService.createComment(create, IP);
        return "rediect:post/" + id;
    }

}
