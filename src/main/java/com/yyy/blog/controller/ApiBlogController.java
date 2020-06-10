package com.yyy.blog.controller;

import com.yyy.blog.model.Blog;
import com.yyy.blog.service.BlogService;
import com.yyy.blog.service.impl.BlogServiceImpl;
import org.jboss.logging.BasicLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ApiBlogController {
    @Autowired
    BlogServiceImpl blogService;

    @GetMapping("/api/blog")
    public ResponseEntity<List<Blog>> getAllNewestBlog(){
        List<Blog> blogList = blogService.findAllNewestBlog();
        if(blogList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<List<Blog>>(blogList,HttpStatus.OK);
    }

    @PostMapping("/api/blog")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/blog/{id}")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog,@PathVariable Long id){
        Optional<Blog> optionalBlog = blogService.findById(id);
        if(!optionalBlog.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(blog,HttpStatus.OK);
    }

    @DeleteMapping("/api/blog/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable long id){
        blogService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
