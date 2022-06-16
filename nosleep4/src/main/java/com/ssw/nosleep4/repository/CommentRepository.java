package com.ssw.nosleep4.repository;


import com.ssw.nosleep4.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findAllByOrderByModifiedAtDesc(Long postId);
        List<Comment> findBypostIdOrderByCreatedAtDesc(Long postId);
}
