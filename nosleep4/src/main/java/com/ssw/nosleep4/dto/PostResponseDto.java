package com.ssw.nosleep4.dto;

import com.ssw.nosleep4.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class PostResponseDto {

        private String nickname;
        private String tilte;
        private String imageurl;

        private LocalDateTime modifiedAt;
        private String content;

    public PostResponseDto(Post post) {
        this.nickname = post.getNickname();
        this.content = post.getContent();
        this.imageurl = post.getImageurl();
        this.tilte = post.getTitle();
        this.modifiedAt = post.getModifiedAt();
    }
}
