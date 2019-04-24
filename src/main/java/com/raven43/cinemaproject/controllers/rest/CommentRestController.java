package com.raven43.cinemaproject.controllers.rest;

import com.raven43.cinemaproject.model.User;
import com.raven43.cinemaproject.model.comment.Comment;
import com.raven43.cinemaproject.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/rest/topic")
public class CommentRestController {

    private final CommentService commentService;


    @Autowired
    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{topicId}")
    public Page<Comment> get(
            @PathVariable Long topicId,
            @AuthenticationPrincipal User user,
            Pageable pageable
    ) {
        return commentService.get(topicId, pageable);
    }

    @PostMapping("/{topicId}")
    public void post(
            @PathVariable Long topicId,
            @AuthenticationPrincipal User user,
            @RequestParam String text
    ) {
        commentService.post(user, topicId, text);
    }

    @DeleteMapping("/{commentId}")
    public void delete(
            @PathVariable Long commentId,
            @AuthenticationPrincipal User user,
            @RequestParam String text
    ) {
        if (user.isAdmin() || Objects.equals(commentService.getComment(commentId).getUser(),user))
            commentService.delete(commentId);
    }

}
