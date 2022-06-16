package com.ssw.nosleep4.dto;

import com.ssw.nosleep4.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
//    private String nickname;
    private Long id;
    private String comment;

    private LocalDateTime modifiedAt;

    private String nickname;

    public CommentResponseDto(Comment comment) {
        this.nickname = comment.getNickname();
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.modifiedAt = comment.getModifiedAt();
    }
}
