package com.yyy.blog.service;

import com.yyy.blog.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findAllBlogNewest();
    void createNewBlog(Blog blog);
    void updateBlog(Blog blog,Long id);

}
