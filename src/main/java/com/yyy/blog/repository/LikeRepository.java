package com.yyy.blog.repository;

import com.yyy.blog.model.Like;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LikeRepository extends PagingAndSortingRepository<Like,Long> {
}
