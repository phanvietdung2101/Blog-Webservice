package com.yyy.blog.service.impl;

import com.yyy.blog.model.Blog;
import com.yyy.blog.repository.BlogRepository;
import com.yyy.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Blog> findAllBlogNewest(){
       return this.blogRepository.findAllByOrderByIdDesc();
    }

    @Override
    public void createNewBlog(Blog blog){
        this.blogRepository.save(blog);
    }

    @Override
    public void updateBlog(Blog blog, Long id) {
        Optional<Blog> optionalBlog = this.blogRepository.findById(id);
        if(!optionalBlog.isPresent()){
            throw new RuntimeException("this blog id isn't existed");
        }
        blog.setId(id);
        this.blogRepository.save(blog);
    }




}
