package com.ssw.nosleep4.controller;


import com.ssw.nosleep4.dto.PostRequestDto;
import com.ssw.nosleep4.dto.PostResponseDto;

import com.ssw.nosleep4.security.UserDetailsImpl;
import com.ssw.nosleep4.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){

        return postService.createPost(requestDto,userDetails);
    }

    @GetMapping("/api/post/show")
    public List<PostResponseDto> showPost(){
        return postService.getPost();
    }

    @PutMapping("/api/post/{id}")
    public PostResponseDto update(@RequestBody PostRequestDto postRequestDto,@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.update(postRequestDto, id, userDetails);
    }
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok().body(postService.delete(id, userDetails));
    }
}
