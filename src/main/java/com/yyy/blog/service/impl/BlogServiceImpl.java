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





}
