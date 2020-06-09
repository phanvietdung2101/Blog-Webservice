package com.yyy.blog.repository;

import com.yyy.blog.model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    List<Blog> findAllByOrderByIdDesc();
}
