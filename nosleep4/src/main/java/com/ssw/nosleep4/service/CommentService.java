package com.ssw.nosleep4.service;

import com.ssw.nosleep4.domain.Comment;
import com.ssw.nosleep4.dto.CommentRequestDto;
import com.ssw.nosleep4.dto.CommentResponseDto;
import com.ssw.nosleep4.repository.CommentRepository;
import com.ssw.nosleep4.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, Long postId, UserDetailsImpl userDetails) {
        if(requestDto.getComment() == ""){
            throw new NullPointerException("내용을 입력해주세요.");
        }
        Comment comment = new Comment(requestDto, postId, userDetails);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> getComment(Long postId) {
            List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
            List<Comment> commentList = commentRepository.findBypostIdOrderByCreatedAtDesc(postId);
            for(int i = 0; i < commentList.size(); i++){
                // 객체생성
                CommentResponseDto commentResponseDto = new CommentResponseDto(commentList.get(i));
                commentResponseDtos.add(commentResponseDto);
            }
            return commentResponseDtos;
        }

    public CommentResponseDto update(CommentRequestDto requestDto, Long id, UserDetailsImpl userDetails) {
            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("댓글이 없습니다.")
            );
            if(requestDto.getComment() == ""){
                throw new NullPointerException("내용을 입력해주세요.");
            }
            comment.update(requestDto,userDetails);
            commentRepository.save(comment);
            return new CommentResponseDto(comment);
        }


    public CommentResponseDto delete(Long id, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 없습니다.")
        );
        if(userDetails.getUser().getNickname().equals(comment.getNickname())){
            commentRepository.delete(comment);
            return new CommentResponseDto(comment);
        }
        throw new IllegalStateException("작성자만 게시글을 삭제 할수있습니다.");
    }
}
