package com.raven43.cinemaproject.repo.comment;

import com.raven43.cinemaproject.model.comment.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicRepo extends PagingAndSortingRepository<Topic, Long> {
}
