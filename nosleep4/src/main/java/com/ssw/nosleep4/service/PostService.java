package com.ssw.nosleep4.service;

import com.ssw.nosleep4.domain.Post;
import com.ssw.nosleep4.domain.User;
import com.ssw.nosleep4.dto.CommentResponseDto;
import com.ssw.nosleep4.dto.PostRequestDto;
import com.ssw.nosleep4.dto.PostResponseDto;
import com.ssw.nosleep4.repository.PostRepository;
import com.ssw.nosleep4.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, UserDetailsImpl userDetails){
        Post post = new Post(requestDto,userDetails.getUser().getNickname());
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> getPost() {
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        List<Post> articleList = postRepository.findAllByOrderByModifiedAtDesc();
        for(int i = 0; i < articleList.size(); i++){
            // 객체생성
            PostResponseDto articleResponseDto = new PostResponseDto(articleList.get(i));
            postResponseDtos.add(articleResponseDto);
        }
        return postResponseDtos;
    }

    public PostResponseDto update(PostRequestDto requestDto, Long id, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        post.update(requestDto,userDetails);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public PostResponseDto delete(Long id,UserDetailsImpl userDetails) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(userDetails.getUser().getNickname().equals(post.getNickname())){
            postRepository.delete(post);
            return new PostResponseDto(post);
        }
        throw new IllegalStateException("작성자만 게시글을 삭제 할수있습니다.");

    }
}
