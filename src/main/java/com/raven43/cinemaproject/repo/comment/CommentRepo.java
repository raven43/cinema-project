package com.raven43.cinemaproject.repo.comment;

import com.raven43.cinemaproject.model.comment.Comment;
import com.raven43.cinemaproject.model.comment.Topic;
import com.raven43.cinemaproject.repo.CustomRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CustomRepo<Comment, Long> {

    Page<Comment> getByTopic(Topic topic, Pageable pageable);

    @Query("select c from Comment c where c.topic = ?1")
    Page<Comment> getByTopicId(Long id, Pageable pageable);

}
