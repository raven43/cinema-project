package com.raven43.cinemaproject.service;

import com.raven43.cinemaproject.model.comment.Comment;
import com.raven43.cinemaproject.model.comment.Topic;
import com.raven43.cinemaproject.model.domain.User;
import com.raven43.cinemaproject.repo.comment.CommentRepo;
import com.raven43.cinemaproject.repo.comment.TopicRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final TopicRepo topicRepo;

    public void post(User user, Topic topic, String text) {
        Comment comment = new Comment(user, topic, text);
        commentRepo.save(comment);
    }

    public void post(User user, Long topicId, String text) {
        Topic topic = topicRepo.findById(topicId).orElseThrow();
        Comment comment = new Comment(user, topic, text);
        commentRepo.save(comment);
    }

    public Page<Comment> get(Topic topic, Pageable pageable) {
        return commentRepo.getByTopic(topic, pageable);
    }

    public Page<Comment> get(Long topicId, Pageable pageable) {
        Topic topic = topicRepo.findById(topicId).orElseThrow();
        return commentRepo.getByTopic(topic, pageable);
    }

    public void delete(Comment comment) {
        commentRepo.delete(comment);
    }

    public void delete(Long commentId) {
        commentRepo.deleteById(commentId);
    }

    public Comment getComment(Long id) {
        return commentRepo.findById(id).orElseThrow();
    }
}
