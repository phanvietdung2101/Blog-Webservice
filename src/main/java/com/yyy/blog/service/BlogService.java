package com.yyy.blog.service;

import com.yyy.blog.model.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> findAllNewestBlog();
    Blog save(Blog blog);
    void delete(Long id);
    Optional<Blog> findById(Long id);
}
