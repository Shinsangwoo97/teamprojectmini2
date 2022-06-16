package com.ssw.nosleep4.domain;

import com.ssw.nosleep4.dto.CommentRequestDto;
import com.ssw.nosleep4.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String comment;

    public Comment(CommentRequestDto requestDto, Long postId, UserDetailsImpl userDetails) {
        this.nickname = userDetails.getUser().getNickname();
        this.comment = requestDto.getComment();
        this.postId = postId;
    }

    public void update(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        this.nickname = userDetails.getUser().getNickname();
        this.comment = requestDto.getComment();
    }
}
