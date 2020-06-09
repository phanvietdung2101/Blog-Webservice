package com.yyy.blog.repository;

import com.yyy.blog.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    Optional<User> findUserByUsername(String username);
}
