package com.yyy.blog.controller;

import com.yyy.blog.model.Blog;
import com.yyy.blog.model.User;
import com.yyy.blog.service.BlogService;
import com.yyy.blog.service.impl.BlogServiceImpl;
import com.yyy.blog.service.impl.UserServiceImpl;
import org.jboss.logging.BasicLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@EnableResourceServer
public class ApiBlogController {
    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/api/blog")
    public ResponseEntity<List<Blog>> getAllNewestBlog(){
        List<Blog> blogList = blogService.findAllNewestBlog();
        if(blogList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<List<Blog>>(blogList,HttpStatus.OK);
    }

    @PostMapping("/api/blog")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, Principal principal){
        try {
            Optional<User> userOptional = userService.findUserByUsername(principal.getName());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                blog.setUser(user);
                blogService.save(blog);
            } else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/blog/{id}")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog,@PathVariable Long id,Principal principal){
        Optional<User> optionalUser;
        Optional<Blog> optionalBlog;
        try {
            optionalUser = userService.findUserByUsername(principal.getName());
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        optionalBlog = blogService.findById(id);
        if(optionalBlog.isPresent() && optionalUser.isPresent()){
            User currentUser = optionalUser.get();
            Blog currentBLog = optionalBlog.get();
            User blogOwner = currentBLog.getUser();
            if (currentUser.getId() != blogOwner.getId()){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            // Xac thuc thanh cong
            blog.setId(id);
            blogService.save(blog);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/api/blog/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable long id,Principal principal){
       if(!authUserOwner(id,principal)){
           return new ResponseEntity<>(HttpStatus.FORBIDDEN);
       }
       blogService.delete(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean authUserOwner(long blogId, Principal principal){
        try {
            Optional<User> optionalUser = userService.findUserByUsername(principal.getName());
            Optional<Blog> optionalBlog = blogService.findById(blogId);
            if(optionalBlog.isPresent() && optionalUser.isPresent()) {
                Blog blog = optionalBlog.get();
                User currentUser = optionalUser.get();
                User blogOwner = blog.getUser();
                if(currentUser.getId() == blogOwner.getId()){
                    return true;
                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
