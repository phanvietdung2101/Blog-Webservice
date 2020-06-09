package com.yyy.blog.repository;

import com.yyy.blog.model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
}
