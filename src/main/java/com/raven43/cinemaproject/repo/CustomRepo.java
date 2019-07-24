package com.raven43.cinemaproject.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CustomRepo<T, ID> extends PagingAndSortingRepository<T, ID> {

    @Query(value = "select * from #{#entityName} e order by RAND()", nativeQuery = true)
    Page<T> getRandom(Pageable pageable);

}
