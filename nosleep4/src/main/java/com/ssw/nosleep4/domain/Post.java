package com.ssw.nosleep4.domain;

import com.ssw.nosleep4.dto.PostRequestDto;
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
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageurl;

    @Column(nullable = false)
    private String content;


    public void update(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        this.nickname = userDetails.getUser().getNickname();
        this.title = requestDto.getTitle();
        this.imageurl = requestDto.getImageurl();
        this.content = requestDto.getContent();
    }

    public Post(PostRequestDto requestDto, String nickname) {
        this.nickname = nickname;
        this.title = requestDto.getTitle();
        this.imageurl = requestDto.getImageurl();
        this.content = requestDto.getContent();
    }

}
