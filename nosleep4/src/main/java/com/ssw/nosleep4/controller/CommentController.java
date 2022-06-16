package com.ssw.nosleep4.controller;

import com.ssw.nosleep4.dto.CommentRequestDto;
import com.ssw.nosleep4.dto.CommentResponseDto;

import com.ssw.nosleep4.security.UserDetailsImpl;
import com.ssw.nosleep4.service.CommentService;
import lombok.RequiredArgsConstructor;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;

    @PostMapping("/api/{postId}/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto,@PathVariable Long postId,@AuthenticationPrincipal UserDetailsImpl userDetails){

        return commentService.createComment(requestDto,postId,userDetails);
    }

    @GetMapping("/api/{postId}/comment")
    public List<CommentResponseDto> showComment(@PathVariable Long postId){
        return commentService.getComment(postId);
    }

    @PutMapping("/api/post/comment/{id}")
    public CommentResponseDto update(@RequestBody CommentRequestDto commentRequestDto,@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.update(commentRequestDto, id,userDetails);
    }
    @DeleteMapping("/api/post/comment/{id}")
    public CommentResponseDto delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return commentService.delete(id, userDetails);
    }
}
