package com.springboot.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.dto.CommentDto;
import com.springboot.dto.PostDto;
import com.springboot.service.CommentService;
import com.springboot.service.PostService;

@Controller
public class CommentController {

    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService,PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    // handler method to create form submit request
    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                @Valid @ModelAttribute("comment") CommentDto commentDto,
                                BindingResult result,
                                Model model){

        PostDto postDto = postService.findPostByUrl(postUrl);
        if(result.hasErrors()){
            model.addAttribute("post", postDto);
            model.addAttribute("comment", commentDto);
            return "blog/blog_post";
        }

        commentService.createComment(postUrl, commentDto);
        return "redirect:/post/" + postUrl;
    }
}
